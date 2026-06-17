package com.svetanis.agents.config;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.adk.plugins.PluginManager;
import com.google.adk.web.AdkWebServer;
import com.google.adk.web.AgentLoader;
import com.google.adk.web.AgentStaticLoader;
import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.a2aclient.CustomerSupportAgent;
import com.svetanis.agents.base.AgentConfigsProvider;
import com.svetanis.agents.base.AppConfig;
import com.svetanis.agents.base.AppConfigProvider;
import com.svetanis.agents.base.serialize.YamlMapperProvider;
import com.svetanis.agents.base.serialize.YamlSerializer;
import com.svetanis.agents.blogger.BlogRootAgent;
import com.svetanis.agents.code.CodeRootAgent;
import com.svetanis.agents.currency.CurrencyRootAgent;
import com.svetanis.agents.devtools.DevToolsRootAgent;
import com.svetanis.agents.plugins.RateLimitPlugin;
import com.svetanis.agents.plugins.RetryPlugin;
import com.svetanis.agents.report.ReportRootAgent;
import com.svetanis.agents.story.StoryRootAgent;
import com.svetanis.agents.traveler.TravelerRootAgent;
import com.svetanis.agents.tutor.TutorRootAgent;
import com.svetanis.agents.zoo.DemoAgent;
import com.svetanis.agents.zoo.researcher.ContentPipelineProvider;

/**
 * Global configuration class for the Agent Development Kit (ADK). This class
 * wires together the foundational beans for the application, including rate
 * limit and retry plugins, YAML serializers, agent configurations, and the
 * static loader that registers all the root agents into the ADK Web Server.
 */
@Configuration
@Import(AdkWebServer.class)
public class AdkConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdkConfig.class);

	@Value("${adk.plugins.rate.limit.requests.per.minute:5.0}")
	private double requestsPerMinute;

	@Value("${adk.plugins.rate.limit.retry.max.attempts:3}")
	private int maxAttempts;

	@Value("${adk.plugins.rate.limit.retry.base.delay:2}")
	private long baseDelay;

	@Value("${adk.agent.model:groq/llama-3.3-70b-versatile}")
	private String model;

	@Bean
	YamlSerializer yamlSerializer() {
		YamlMapperProvider mapper = new YamlMapperProvider();
		return new YamlSerializer(mapper.get());
	}

	@Bean
	Map<String, AgentConfig> agentConfigs() {
		return new AgentConfigsProvider(yamlSerializer()).get();
	}

	@Bean
	AppConfig appConfig() {
		return new AppConfigProvider(plugins(), agentConfigs()).get();
	}

	@Bean
	RateLimitPlugin rateLimitPlugin() {
		String msg = "ADK Config: Creating RateLimitPlugin with %s RPM";
		LOGGER.info(String.format(msg, this.requestsPerMinute));
		double requestsPerSecond = this.requestsPerMinute / 60.0;
		return new RateLimitPlugin(requestsPerSecond);
	}

	@Bean
	RetryPlugin retryPlugin() {
		String msg = "ADK Config: Creating RetryPlugin with maxAttempts=%s and baseDelay=%s ms";
		LOGGER.info(String.format(msg, this.maxAttempts, this.baseDelay));
		return new RetryPlugin(this.maxAttempts, Duration.ofSeconds(this.baseDelay));
	}

	@Bean
	PluginManager plugins() {
		LOGGER.info("ADK Config: Wiring global plugins list [RateLimit, Retry]");
		return new PluginManager(List.of(rateLimitPlugin(), retryPlugin()));
	}

	@Bean
	AgentLoader agentLoader() {
		return new AgentStaticLoader(//
				new TutorRootAgent(appConfig()).get(), //
				new BlogRootAgent(appConfig()).get(), //
				new CodeRootAgent(appConfig()).get(), //
				new ReportRootAgent(appConfig()).get(), //
				new TravelerRootAgent(appConfig()).get(), //
				new CurrencyRootAgent(appConfig()).get(), //
				new DevToolsRootAgent(appConfig()).get(), //
				new StoryRootAgent(appConfig()).get(), //
				new CustomerSupportAgent(appConfig()).get());//
	}
}
