package com.svetanis.agents.devtools;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.mcp.McpToolset;
import com.google.adk.tools.mcp.StreamableHttpServerParameters;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

// https://adk.dev/integrations/github/
// https://docs.github.com/en/copilot/tutorials/customization-library/prompt-files/create-readme
// https://docs.github.com/en/copilot/tutorials/customization-library/custom-agents/your-first-custom-agent

public class ReadmeAgent implements Provider<LlmAgent> {

  private static final String DRA_KEY = "devtools.readme";

  public ReadmeAgent(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    StreamableHttpServerParameters params = new ServerParamsProvider().get();
    try (McpToolset mcp = new McpToolset(params)) {
      AgentContext ctx = ctx(mcp);
      return new LlmAgentProvider(ctx).get();
    }
  }

  private AgentContext ctx(McpToolset mcp) {
    // TODO: add Tool predicate
    AgentConfig config = agentConfig(DRA_KEY);
    return AgentContext//
        .builder()//
        .withConfig(config)//
        .withTools(mcp.getTools(null).toList().blockingGet())//
        .withPlugins(appConfig.getPlugins())//
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}
