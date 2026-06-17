package com.svetanis.agents.devtools;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.BuiltInCodeExecutionTool;
import com.google.common.collect.ImmutableList;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

public class BuilderAgent implements Provider<LlmAgent> {

  private static final String BLD_KEY = "devtools.builder";

  public BuilderAgent(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    AgentContext ctx = ctx();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentContext ctx() {
    AgentConfig config = agentConfig(BLD_KEY);
    return AgentContext//
        .builder()//
        .withConfig(config)//
        .withTools(ImmutableList.of(new BuiltInCodeExecutionTool()))//
        .withPlugins(appConfig.getPlugins())//
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}
