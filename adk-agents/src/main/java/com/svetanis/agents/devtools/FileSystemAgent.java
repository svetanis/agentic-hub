package com.svetanis.agents.devtools;

import static java.nio.charset.StandardCharsets.UTF_8;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.io.ByteSource;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;
import com.svetanis.agents.utils.Files;
import com.svetanis.agents.utils.Strings;

import jakarta.inject.Provider;

public class FileSystemAgent implements Provider<LlmAgent> {

  private static final String TARGET = "./target/temp";
  private static final String DFA_KEY = "devtools.filesystem";

  private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemAgent.class);

  public FileSystemAgent(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    FunctionTool fst = FunctionTool.create(FileSystemAgent.class, "writeFile");
    AgentConfig config = agentConfig(DFA_KEY);
    AgentContext ctx = AgentContext//
        .builder()//
        .withConfig(config)//
        .withTools(fst)//
        .withPlugins(appConfig.getPlugins())//
        .build();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }

  // TODO: build FileSystem MCP server
  @Schema(name = "write", description = "Writes a content to the destination file ")
  public static Map<String, String> writeFile(//
      @Schema(name = "destinationPath", description = "the destination path", optional = true) String destinationPath, //
      @Schema(name = "content", description = "content to be written to destination file") String content) {
    String target = Joiner.on("/").join(TARGET, "README.md");
    String path = Optional.fromNullable(destinationPath).or(target);
    try {
      File to = Files.canonical(new File(path));
      ByteSource bytes = Strings.asByteSource(content, UTF_8);
      Files.write(bytes, to);
      String msg = String.format("File saved to %s", path);
      LOGGER.info(msg);
      return Map.of("status", "OK", "msg", msg);
    } catch (IOException e) {
      return Map.of("exception", e.getMessage());
    }
  }
}
