package com.svetanis.agents.devtools;

import static com.google.api.client.util.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableList.copyOf;

import java.util.ArrayList;
import java.util.List;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.AgentTool;
import com.google.adk.tools.BaseTool;
import com.google.common.collect.ImmutableList;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

/**
 * The Root Agent Provider for the DevTools module.
 * This agent orchestrates software development tasks such as generating
 * README files, building models, and committing code to repositories.
 */
public class DevToolsRootAgent implements Provider<LlmAgent> {

  private static final String DRA_KEY = "devtools.root";

  public DevToolsRootAgent(AppConfig appConfig) {
    this.appConfig = checkNotNull(appConfig);
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    AgentConfig config = agentConfig(DRA_KEY);
    AgentContext ctx = AgentContext.builder()//
        .withConfig(config)//
        .withTools(tools())//
        .withPlugins(appConfig.getPlugins())//
        .build();//
    return new LlmAgentProvider(ctx).get();
  }

  private ImmutableList<BaseTool> tools() {
    List<BaseTool> list = new ArrayList<>();
    list.add(AgentTool.create(new ReadmeAgent(appConfig).get()));
    list.add(AgentTool.create(new BuilderAgent(appConfig).get()));
    list.add(AgentTool.create(new FileSystemAgent(appConfig).get()));
    return copyOf(list);
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}
