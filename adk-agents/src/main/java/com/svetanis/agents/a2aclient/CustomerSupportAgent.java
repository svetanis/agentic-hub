package com.svetanis.agents.a2aclient;

import static com.google.api.client.util.Preconditions.checkNotNull;
import static java.util.Arrays.asList;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

public class CustomerSupportAgent implements Provider<LlmAgent> {

  private static final String CSA_KEY = "a2aclient.customer.support";

  public CustomerSupportAgent(AppConfig appConfig) {
    this.appConfig = checkNotNull(appConfig, "appConfig");
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    AgentContext ctx = agentContext();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentContext agentContext() {
    BaseAgent pca = new RemoteProductCatalogAgent().get();
    AgentConfig config = agentConfig(CSA_KEY);
    return AgentContext//
        .builder()//
        .withConfig(config)//
        .withPlugins(appConfig.getPlugins())//
        .withSubAgents(asList(pca))//
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}