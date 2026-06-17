package com.svetanis.agents.story;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.LoopAgent;
import com.google.adk.tools.ExitLoopTool;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

public class RefinementLoop implements Provider<LoopAgent> {

  private static final String SCA_KEY = "story.critic";
  private static final String SRA_KEY = "story.refiner";

  private static final String DESC = """
      Improves essay based on critique or signals completion.
      """;

  public RefinementLoop(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  private final AppConfig appConfig;

  @Override
  public LoopAgent get() {
    AgentConfig criticConfig = agentConfig(SCA_KEY);
    AgentContext criticCtx = AgentContext.builder()
        .withConfig(criticConfig)
        .withPlugins(appConfig.getPlugins())
        .build();
    LlmAgent critic = new LlmAgentProvider(criticCtx).get();
    
    AgentConfig config = agentConfig(SRA_KEY);
    AgentContext ctx = AgentContext.builder()
        .withConfig(config)
        .withTools(ExitLoopTool.INSTANCE)
        .withPlugins(appConfig.getPlugins())
        .build();
    LlmAgent refiner = new LlmAgentProvider(ctx).get();
    
    return LoopAgent.builder().name("RefinementLoop") //
        .description(DESC) //
        .subAgents(critic, refiner) //
        .maxIterations(3) //
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}
