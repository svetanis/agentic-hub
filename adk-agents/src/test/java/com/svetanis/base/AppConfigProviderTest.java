package com.svetanis.base;

import static com.google.common.collect.Maps.fromProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import com.svetanis.agents.base.AppConfigProvider;

// https://github.com/google/adk-java/blob/main/contrib/samples/configagent/core_generate_content_config_config/root_agent.yaml
public class AppConfigProviderTest {

  @Test
  public void test() throws IOException {
    Map<String, String> props = fromProperties(loadProperties("application.properties"));
    System.out.println(props.size());
    for (String key : props.keySet()) {
      System.out.println(key + ":" + props.get(key));
    }
  }

  private Properties loadProperties(String resource) {
    Properties props = new Properties();
    try (InputStream in = AppConfigProvider.class.getClassLoader().getResourceAsStream(resource)) {
      if (in == null) {
        return new Properties();
      }
      props.load(in);
      return props;
    } catch (Throwable e) {
      e.printStackTrace();
      throw new IllegalStateException(e);
    }
  }

}
