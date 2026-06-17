package com.svetanis.agents.story;

import static com.google.api.client.util.Preconditions.checkNotNull;

import com.google.adk.agents.LlmAgent;
import com.google.adk.agents.LoopAgent;
import com.google.adk.agents.SequentialAgent;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

/**
 * The Root Agent Provider for the Story module.
 * Instead of a standard LlmAgent, this provides a SequentialAgent that sets up
 * a pipeline for drafting, critiquing, and refining a story.
 */
public class StoryRootAgent implements Provider<SequentialAgent> {

  private static final String DESC = "Story writing and refinement system";
  private static final String SWA_KEY = "story.writer";

  public StoryRootAgent(AppConfig appConfig) {
    this.appConfig = checkNotNull(appConfig, "appConfig");
  }

  private final AppConfig appConfig;

  @Override
  public SequentialAgent get() {
    AgentConfig config = agentConfig(SWA_KEY);
    AgentContext ctx = AgentContext.builder()
        .withConfig(config)
        .withPlugins(appConfig.getPlugins())
        .build();
        
    LlmAgent writer = new LlmAgentProvider(ctx).get();
    LoopAgent refinementLoop = new RefinementLoop(appConfig).get();
    return SequentialAgent.builder() //
        .name("StoryRefinementSystem") //
        .description(DESC) //
        .subAgents(writer, refinementLoop) //
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}
