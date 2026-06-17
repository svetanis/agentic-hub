# DevTools Agent Module

## Overview
The DevTools Module provides a suite of developer-focused AI agents designed to automate repetitive coding, repository management, and documentation tasks. These agents can analyze workspaces, generate documentation, build applications, and manage Git operations.

## How It Works

The system is orchestrated by the `DevToolsRootAgent` which routes tasks to specialized sub-agents:

- **ReadmeAgent**: Scans a project directory and automatically generates or updates comprehensive `README.md` documentation.
- **FileSystemAgent**: Analyzes the file system and project structure to assist other agents.
- **BuilderAgent**: Handles compiling and building projects using Maven or other build systems.
- **GithubCommitterAgent**: Automates the process of creating Git commits and pushing changes to GitHub.

## Flow Diagram

```
User Input
    ↓
DevToolsRootAgent (Coordinator)
    ↓
(Delegates based on intent)
    ├─→ ReadmeAgent
    ├─→ FileSystemAgent
    ├─→ BuilderAgent
    └─→ GithubCommitterAgent
```

## Running the Project

To start the DevTools agent server:

```bash
export GROQ_API_KEY="YOUR_GROQ_API_KEY"
export COPILOT_GITHUB_TOKEN="YOUR_GITHUB_TOKEN"
mvn compile exec:java -Dexec.mainClass="com.svetanis.agents.MultiAgentsSystem"
```

Once running, you can ask the agent to:
- "Generate a README for the current project"
- "Commit the recent changes with a descriptive message"
- "Build the project and show me the output"

## Configuration
Agent behaviors are configured using YAML files located in `src/main/resources/agent-configs/devtools/`. All agents utilize the Groq Llama 3.3 70B Versatile model by default.
