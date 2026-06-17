# 🤖 ADK Agents

*A module showcase of intelligent, domain-specific AI agents built using the Google Agent Development Kit (ADK) and configured dynamically.*

---

## 📖 Overview

`adk-agents` is a library demonstrating how to design, configure, and orchestrate single-agent and multi-agent systems using the ADK framework. All agents in this module leverage **Model Prism**, a universal model registry that permits swapping LLM providers (Google Gemini, Groq, Ollama, OpenRouter, OpenAI) with zero code changes.

---

## ⚙️ Module Technology Stack

| Component | Technology / Framework | Details |
| :--- | :--- | :--- |
| **Language** | Java 25 | Standard JVM baseline |
| **Agent Framework** | Google Agent Development Kit (ADK) | Declarative YAML-based agent definitions |
| **Model Registry** | Model Prism | Multi-provider LLM connector (OpenRouter, Groq, etc.) |
| **Orchestration** | Parallel & Sequential | Orchestrated multi-agent interactions |
| **Tools** | Google Search, Web APIs, MCP | Custom functional extensions injected into LLMs |

---

## 🛠️ Key Features & Agent Profiles

*   **Blogger Agent**: A sophisticated multi-agent pipeline capable of researching topics, drafting blog posts, revising content, and publishing.
*   **Currency Agent**: An agent that performs live exchange rate lookups and calculations by fetching external data tools.
*   **GitHub Readme Agent**: A metadata agent designed to scan a repository and generate structured, comprehensive `README.md` files.
*   **Tutor Agent**: An interactive educational agent tailored to explain complex subjects step-by-step.
*   **Declarative Configurations**: Agents are configured in clean YAML blueprints specifying roles, instructions, and tools.
*   **Universal LLM Support**: Defaults use `groq/llama-3.3-70b-versatile`, but easily swaps to local Ollama (`ollama/llama3`) or OpenAI via prefix configuration.

---

## 🚀 Getting Started

### Prerequisites

*   **Java Development Kit (JDK) 25** or higher.
*   **Apache Maven 3.9** or higher.
*   Your model provider's API key set in your environment (e.g., `export GROQ_API_KEY="your-key"`).

### Installation & Run

1. Build the module from the root `agentic-hub` directory:
   ```bash
   mvn clean install
   ```

2. Run the Multi-Agent System main class:
   ```bash
   export GROQ_API_KEY="your-key"
   mvn compile exec:java -Dexec.mainClass="com.svetanis.agents.MultiAgentsSystem" -pl adk-agents
   ```

Once the application starts, it hosts the ADK runtime. You can interact with all configured agents via the ADK web interface or standard endpoints.

---

## 📂 Agent Configurations

For specific configuration blueprints and implementations:
*   [Blogger Agent Blueprint](src/main/java/com/svetanis/agents/blogger/README.md)
*   [Tutor Agent Blueprint](src/main/java/com/svetanis/agents/tutor/README.md)
*   [Code Agent Blueprint](src/main/java/com/svetanis/agents/code/README.md)
*   [Currency Agent Blueprint](src/main/java/com/svetanis/agents/currency/README.md)
*   [Report Agent Blueprint](src/main/java/com/svetanis/agents/report/README.md)
*   [Traveler Agent Blueprint](src/main/java/com/svetanis/agents/traveler/README.md)

---

## 🤝 Help & Contributing

*   **ADK core**: See [Official ADK Documentation](https://github.com/Azure/adk)
*   **GitHub Issues**: To submit bugs or suggest agent implementations, open an issue on the parent [agentic-hub issues page](https://github.com/svetanis/agentic-hub/issues).
