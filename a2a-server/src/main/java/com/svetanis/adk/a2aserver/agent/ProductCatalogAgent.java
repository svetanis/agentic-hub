package com.svetanis.adk.a2aserver.agent;

import static com.google.api.client.util.Preconditions.checkNotNull;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.FunctionTool;
import com.google.common.collect.ImmutableList;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

/**
 * The Root Agent Provider for the Product Catalog module.
 * This agent interacts with the ProductCatalogTools to query product inventory,
 * prices, and stock availability on behalf of external requests.
 */
public class ProductCatalogAgent implements Provider<LlmAgent> {

  private static final String PCA_KEY = "products.catalog";

  public ProductCatalogAgent(AppConfig appConfig) {
    this.appConfig = checkNotNull(appConfig, "appConfig");
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    AgentContext ctx = agentContext();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentContext agentContext() {
    FunctionTool tool = FunctionTool.create(ProductCatalogTools.class, "getProductInfo");
    AgentConfig config = agentConfig(PCA_KEY);
    return AgentContext//
        .builder()//
        .withConfig(config)//
        .withPlugins(appConfig.getPlugins())//
        .withTools(ImmutableList.of(tool))//
        .build();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}