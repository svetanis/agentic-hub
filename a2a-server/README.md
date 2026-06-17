# A2A Server (Product Catalog)

## Overview
The A2A Server is a Quarkus-based application that implements the Agent-to-Agent (A2A) protocol. It hosts a `ProductCatalogAgent` that can be queried by external agents to retrieve real-time inventory, pricing, and stock information.

This project demonstrates how to wrap an ADK (Agent Development Kit) agent inside a lightweight, reactive web server using Quarkus, making it discoverable and interoperable over HTTP.

## Key Features
- **AgentCard Generation**: Exposes an `.well-known/agent-card.json` endpoint for agent discovery.
- **ProductCatalogAgent**: An LLM agent configured with tools (`ProductCatalogTools`) to mock database queries for electronic products.
- **Quarkus + ADK Integration**: Uses CDI beans (`AgentExecutorProducer`) to inject the ADK runtime into the Quarkus server.
- **Java 25 Ready**: Fully configured to compile and run on Java 25.

## Running the Server

Make sure you have JDK 25 and Maven installed.

```bash
export GROQ_API_KEY="YOUR_GROQ_API_KEY"

# Start the Quarkus Dev Server
mvn quarkus:dev
```

The server will start on `http://localhost:9090`.

## Endpoints
- **Agent Card**: `GET http://localhost:9090/.well-known/agent-card.json`
- **Interact**: Use an ADK client to POST messages to the agent endpoint defined in the card.

## Configuration
The `ProductCatalogAgent` is configured via YAML in `src/main/resources/agents/products/catalog-agent.yaml`. It uses the `groq/llama-3.3-70b-versatile` model by default.
