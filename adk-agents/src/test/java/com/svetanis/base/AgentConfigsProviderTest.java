package com.svetanis.base;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.svetanis.agents.base.AgentConfig;
import com.svetanis.agents.base.AgentConfigsProvider;

public class AgentConfigsProviderTest {

  @Test
  public void testAgentConfigurationsLoadSuccessfully() throws IOException {
    AgentConfigsProvider provider = new AgentConfigsProvider();
    Map<String, AgentConfig> map = provider.get();
    
    assertNotNull(map, "The configuration map should not be null");
    assertFalse(map.isEmpty(), "The configuration map should not be empty");
  }
}
