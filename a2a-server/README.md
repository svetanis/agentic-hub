# 🌐 A2A Server

*A Quarkus-based microservice implementing the Agent-to-Agent (A2A) protocol to expose ADK agents over HTTP.*

---

## 📖 Overview

`a2a-server` hosts the `ProductCatalogAgent`, allowing external agents to query real-time product catalogs, pricing, and stock status using standard JSON-RPC communication. 

This module serves as a production-grade template for wrapping ADK-configured agents inside a reactive, high-performance Quarkus server. It handles registration, discovery metadata, and network dispatch.

---

## ⚙️ Module Technology Stack

| Component | Technology / Framework | Details |
| :--- | :--- | :--- |
| **Language** | Java 25 | Standard JVM baseline |
| **Server Framework** | Quarkus 3.30+ | Reactive CDI container and REST endpoints |
| **Agent Framework** | Google Agent Development Kit (ADK) | Underlying agent logic and runtime |
| **A2A Protocol** | Agent-to-Agent Java SDK | JSON-RPC wrapper and discovery schema |
| **Core Dependency** | `adk-agents` (Sibling module) | Leverages the shared agent definitions |

---

## 🛠️ Key Features

*   **AgentCard Generation**: Exposes standard discovery metadata via `/.well-known/agent-card.json`.
*   **CDI Lifecycle Bindings**: Uses Quarkus dependency injection (`AgentExecutorProducer`) to bootstrap the ADK core runtime context seamlessly.
*   **ProductCatalogAgent**: Configured with tools (`ProductCatalogTools`) to mock database operations for electronics inventory.
*   **Reactive Endpoints**: Built with RESTEasy Reactive for fast response times.

---

## 🚀 Getting Started

### Prerequisites

*   **Java Development Kit (JDK) 25** or higher.
*   **Apache Maven 3.9** or higher.
*   Your model provider's API key set in your environment (e.g., `export GROQ_API_KEY="your-key"`).

### Running the Server

Start the server in Quarkus development mode from the root `agentic-hub` folder:

```bash
export GROQ_API_KEY="your-key"
mvn quarkus:dev -pl a2a-server
```

The server binds to `http://localhost:9090` by default.

---

## 📌 Available Endpoints

*   **Discovery Card**: `GET http://localhost:9090/.well-known/agent-card.json`
    *   Exposes properties, inputs, and capabilities of the catalog agent.
*   **Interactions**: External clients communicate with the catalog agent by sending A2A-compliant JSON-RPC messages to the interaction endpoint specified in the agent card.

---

## 📂 Configuration

*   **Agent Definition**: The catalog agent instructions, parameters, and tools are configured in the YAML blueprint located at [catalog-agent.yaml](src/main/resources/agents/products/catalog-agent.yaml).
*   **Quarkus Configuration**: HTTP ports and platform settings are managed inside [application.properties](src/main/resources/application.properties).
