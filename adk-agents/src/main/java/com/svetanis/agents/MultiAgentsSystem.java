package com.svetanis.agents;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.svetanis.models.spi.ModelProvider;
import com.github.svetanis.models.spi.ModelProviderRegistry;

// mvn compile exec:java -Dexec.mainClass=com.svetanis.agents.MultiAgentsSystem

/**
 * The main entry point for the Multi-Agents System application.
 * This class initializes the Spring Boot context and registers all
 * available LLM model providers (e.g., Groq, Ollama, OpenRouter).
 */
@SpringBootApplication
public class MultiAgentsSystem {
  private static final String PAT = " %-20s pattern: %s%n";

  public static void main(String[] agrs) {
    List<ModelProvider> registered = ModelProviderRegistry.registerAll();
    showProviders(registered);
    SpringApplication.run(MultiAgentsSystem.class, agrs);
  }
  

  public static void showProviders(List<ModelProvider> registered) {
    System.out.println("Registered providers:");
    registered.forEach(p -> System.out.printf(PAT, p.getClass().getSimpleName(), p.modelPattern()));
  }

}
