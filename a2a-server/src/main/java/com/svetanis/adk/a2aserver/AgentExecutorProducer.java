package com.svetanis.adk.a2aserver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.google.adk.a2a.executor.AgentExecutorConfig;
import com.google.adk.agents.LlmAgent;
import com.google.adk.artifacts.InMemoryArtifactService;
import com.google.adk.plugins.PluginManager;
import com.google.adk.sessions.InMemorySessionService;
import com.svetanis.adk.a2aserver.agent.ProductCatalogAgent;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentConfigsProvider;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.AppConfigProvider;
import com.svetanis.agents.plugins.RateLimitPlugin;
import com.svetanis.agents.plugins.RetryPlugin;

import io.a2a.server.agentexecution.AgentExecutor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

// mvn clean install
// mvn quarkus:dev

// http://localhost:9090/.well-known/agent-card.json

/**
 * Produces the {@link AgentExecutor} instance that hosts the a2a_server agents.
 * This configures the RateLimit and Retry plugins, sets up the Application context,
 * and initializes the ProductCatalogAgent as the primary entry point.
 */
@ApplicationScoped
public class AgentExecutorProducer {

  @Produces
  public AgentExecutor agentExecutor() {
    RateLimitPlugin rateLimitPlugin = new RateLimitPlugin(5.0 / 60.0);
    RetryPlugin retryPlugin = new RetryPlugin(3, Duration.ofSeconds(2));
    PluginManager plugins = new PluginManager(List.of(rateLimitPlugin, retryPlugin));
    Map<String, AgentConfig> configs = new AgentConfigsProvider().get();
    AppConfig appConfig = new AppConfigProvider(plugins, configs).get();

    LlmAgent agent = new ProductCatalogAgent(appConfig).get();
    InMemorySessionService sessionService = new InMemorySessionService();
    InMemoryArtifactService artifactService = new InMemoryArtifactService();
    return new com.google.adk.a2a.executor.AgentExecutor.Builder()//
        .agent(agent)//
        .appName("ProductCatalogApp")//
        .sessionService(sessionService)//
        .artifactService(artifactService)//
        .agentExecutorConfig(AgentExecutorConfig.builder().build())//
        .build();
  }
}
