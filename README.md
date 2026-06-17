# 🌌 Agentic Hub

*A unified workspace and playground for building, exploring, and deploying production-grade agentic architectures, multi-agent networks, and communication protocols in the JVM.*

---

## 📖 Overview

`agentic-hub` is a framework-agnostic repository designed as a learning playground with near-production-level code quality. It consolidates agent implementations, agent-to-agent interfaces, and Model Context Protocol (MCP) integrations across multiple frameworks (such as Google ADK, Spring AI, and more).

This monorepo is structured as a Maven multi-module project to facilitate clean dependency separation, common build practices, and modular development of intelligent agent services.

---

## 🛠️ Repository Structure

*   📂 **[adk-agents](adk-agents)**: A collection of diverse, domain-specific AI agents built using the Google Agent Development Kit (ADK) and the Model Prism registry.
*   📂 **[a2a-server](a2a-server)**: A Quarkus-based server wrapping ADK agents to expose them over the Agent-to-Agent (A2A) protocol.

---

## ⚙️ Core Technology Stack

| Component | Technology / Framework | Details |
| :--- | :--- | :--- |
| **Language** | Java 25 | Standard JVM baseline for modern features and performance |
| **Agent Frameworks** | Google ADK, *Spring AI (upcoming)* | Declarative and programmatic agent orchestration |
| **Protocols** | A2A, *MCP (upcoming)* | Agent-to-agent and tool-context interoperability |
| **Server Runtime** | Quarkus, Spring Boot | Reactive, lightweight, and fast-starting servers |
| **LLMs (Model Prism)** | Gemini, Groq, Ollama, OpenRouter | Unified model registry for flexible provider mapping |
| **Build System** | Maven 3.9+ | Multi-module build and packaging |

---

## 🚀 Getting Started

### Prerequisites

*   **Java Development Kit (JDK) 25** or higher.
*   **Apache Maven 3.9** or higher.
*   Required **API Keys** for your chosen LLM provider (e.g., `GROQ_API_KEY`, `GEMINI_API_KEY`).

### Installation

Clone the repository and compile the complete project from the root folder:

```bash
git clone https://github.com/svetanis/agentic-hub.git
cd agentic-hub
mvn clean install
```

### Running the Submodules

For details on running, testing, and configuring the individual modules, please refer to their respective READMEs:

*   **[adk-agents README](adk-agents/README.md)**: Details on starting the multi-agent system and configuring custom YAML agents.
*   **[a2a-server README](a2a-server/README.md)**: Instructions on running the Quarkus A2A server and calling the product catalog agent over HTTP.

---

## 🤝 Contributing & Maintainers

### Maintainer
*   **svetanis** ([@svetanis](https://github.com/svetanis))
