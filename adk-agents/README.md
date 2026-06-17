# ADK Agents

## What the project does

This repository, `adk-agents`, showcases a collection of diverse Artificial Intelligence (AI) agents built using the Agent Development Kit (ADK). The project provides practical examples of how to design, configure, and run intelligent agents for various tasks, demonstrating the power and flexibility of the ADK framework. This project specifically leverages **Google's Gemini models** through the ADK and integrates with **GitHub Copilot** for development assistance.

Each agent in this repository is designed to address a specific problem domain, offering insights into multi-agent systems, tool integration, and prompt engineering within the ADK ecosystem, with a focus on the Google AI platform.

## Why the project is useful

`adk-agents` serves as an invaluable resource for developers looking to:

*   **Learn ADK**: Understand the core concepts and implementation patterns of the Agent Development Kit through concrete, runnable examples, particularly with Google's AI models.
*   **Build Custom Agents**: Get a head start on developing their own AI agents by adapting existing examples, specifically tailored for Gemini.
*   **Explore Agent Capabilities**: See how different agents can collaborate or function independently to achieve complex goals using the ADK and Google Gemini.
*   **Rapid Prototyping**: Quickly set up and experiment with various agent configurations using declarative YAML definitions.
*   **Leverage Google AI**: Understand how to integrate and utilize Google Gemini models effectively within agent-based applications.

## ⚙️ Technology Stack

- **Language:** Java 25
- **Framework:** Google Agent Development Kit (ADK)
- **LLM Configuration (Model Prism):** The ADK supports a universal model registry ("Model Prism") that allows you to easily plug in any OpenAI-compatible models, OpenRouter, Ollama, or Groq. The agent configurations currently use `groq/llama-3.3-70b-versatile` as a working example, but they can easily be swapped.
- **Configuration:** YAML-based agent definitions
- **Execution:** Sequential and Parallel agent orchestration
- **Tools:** Google Search, Web APIs, Custom extensions, MCP Toolset

## Key features include:

*   **Modular Agent Design**: Each agent is independently structured, making it easy to understand, modify, and extend.
*   **Declarative Configuration**: Agents are configured using YAML files, defining their roles, goals, and the tools they can utilize.
*   **Diverse Agent Examples**:
    *   **Blogger Agent**: A sophisticated multi-agent system capable of researching topics, generating blog post drafts, refining content, and publishing.
    *   **Currency Agent**: An agent designed for currency conversion and calculations, demonstrating tool use for external data fetching.
    *   **GitHub Readme Agent**: An agent that can analyze a GitHub repository and generate a comprehensive `README.md` file (meta-!).
    *   **Tutor Agent**: An interactive educational agent capable of providing tutoring in various subjects.
*   **Java-based Implementation**: Leverages the robust Java ecosystem for building scalable and performant agent applications.
*   **Universal Model Prism**: All agents are wired using the Model Prism registry. You are not locked into any single provider. While the examples use Groq, any OpenAI-compatible API, OpenRouter, or local Ollama model can be plugged in by prefixing the model string in the YAML (e.g., `openrouter/...`, `ollama/...`, `groq/...`).

## How users can get started

To get started with the `adk-agents` project, follow these steps:

### Prerequisites

*   **Java Development Kit (JDK) 25 or higher**: Ensure you have a compatible JDK installed.
*   **Apache Maven 3.6.3 or higher**: Maven is used for building and managing the project.
*   **API Keys**: The examples currently use Groq, requiring the `GROQ_API_KEY` environment variable. However, depending on which provider you decide to use through the Model Prism registry, you can define other keys such as `OPENROUTER_API_KEY`, `OPENAI_API_KEY`, etc.

### Installation

1.  **Clone the repository**:
```bash
git clone https://github.com/svetanis/adk-agents.git
cd adk-agents
```

2.  **Build the project**:
    Navigate to the project root directory and build the project using Maven:
```bash
mvn clean install
```
    This command compiles the Java code, runs tests, and packages the agents.

### Usage Examples

The project now uses a centralized `MultiAgentsSystem` application runner that wires and serves all agents using `AdkConfig`.

#### Starting the ADK Web Server

To run the Multi-Agents System:
```bash
# Set your Groq API key (replace with your actual key)
export GROQ_API_KEY="YOUR_GROQ_API_KEY"

# Run the MultiAgentsSystem Application
mvn compile exec:java -Dexec.mainClass="com.svetanis.agents.MultiAgentsSystem"
```

Once the server starts, all agents (Blogger, Currency, GitHub Readme, Tutor, etc.) are available for interaction through the ADK Web UI or programmatic endpoints.

For more detailed instructions and agent configurations, refer to their respective READMEs:
- [Blogger Agent](src/main/java/com/svetanis/agents/blogger/README.md)
- [Tutor Agent](src/main/java/com/svetanis/agents/tutor/README.md)
- [Code Agent](src/main/java/com/svetanis/agents/code/README.md)
- [Currency Agent](src/main/java/com/svetanis/agents/currency/README.md)
- [Report Agent](src/main/java/com/svetanis/agents/report/README.md)
- [Traveler Agent](src/main/java/com/svetanis/agents/traveler/README.md)

## Where users can get help

For assistance with `adk-agents`, consider the following resources:

*   **ADK Documentation**: For general information and guides on the Agent Development Kit, refer to the [official ADK documentation](https://github.com/Azure/adk).
*   **Google AI Studio Documentation**: For specific details on using Google Gemini models, refer to the [Google AI Studio documentation](https://ai.google.dev/).
*   **GitHub Issues**: If you encounter any issues, have questions, or want to suggest new features, please open an issue on the [GitHub Issues page](https://github.com/svetanis/adk-agents/issues).

## Who maintains and contributes

### Maintainer

*   **svetanis** ([@svetanis](https://github.com/svetanis))

