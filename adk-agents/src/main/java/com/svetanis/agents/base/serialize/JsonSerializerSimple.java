package com.svetanis.agents.base.serialize;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;
import com.google.common.io.Closer;

public final class JsonSerializerSimple {

	public <T> T read(ByteSource source, Class<T> type) throws IOException {
		checkNotNull(source, "source");
		checkNotNull(type, "type");
		Closer closer = Closer.create();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			InputStream in = closer.register(source.openBufferedStream());
			return mapper.readValue(in, type);
		} catch (Throwable e) {
			throw closer.rethrow(e);
		} finally {
			closer.close();
		}
	}

}
