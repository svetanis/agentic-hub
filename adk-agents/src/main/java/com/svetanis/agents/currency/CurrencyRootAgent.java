package com.svetanis.agents.currency;

import static com.google.api.client.util.Preconditions.checkNotNull;

import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.AgentTool;
import com.google.adk.tools.BuiltInCodeExecutionTool;
import com.google.adk.tools.FunctionTool;
import com.google.common.collect.ImmutableList;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentContext;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.LlmAgentProvider;

import jakarta.inject.Provider;

/**
 * The Root Agent Provider for the Currency module.
 * This agent specializes in answering questions related to currency,
 * leveraging external tools like the ExchangeRateTool for accurate data.
 */
public class CurrencyRootAgent implements Provider<LlmAgent> {

  private static final String CRA_KEY = "currency.root";
  private static final String CCA_KEY = "currency.calculator";

  public CurrencyRootAgent(AppConfig appConfig) {
    this.appConfig = checkNotNull(appConfig, "appConfig");
  }

  private final AppConfig appConfig;

  @Override
  public LlmAgent get() {
    AgentContext ctx = agentContext();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentContext agentContext() {
    FunctionTool pmf = FunctionTool.create(CurrencyTools.class, "paymentMethodFee");
    FunctionTool exr = FunctionTool.create(CurrencyTools.class, "exchangeRate");
    AgentTool cat = AgentTool.create(calculatorAgent());
    AgentConfig config = agentConfig(CRA_KEY);
    return AgentContext//
        .builder()//
        .withConfig(config)//
        .withTools(ImmutableList.of(pmf, exr, cat))//
        .withPlugins(appConfig.getPlugins())//
        .build();
  }

  private LlmAgent calculatorAgent() {
    AgentConfig config = agentConfig(CCA_KEY);
    AgentContext ctx = AgentContext//
        .builder()//
        .withConfig(config)//
        .withTools(ImmutableList.of(new BuiltInCodeExecutionTool()))//
        .withPlugins(appConfig.getPlugins())//
        .build();
    return new LlmAgentProvider(ctx).get();
  }

  private AgentConfig agentConfig(String key) {
    String agentKey = appConfig.getProperties().get(key).trim();
    return appConfig.getAgentConfigs().get(agentKey);
  }
}