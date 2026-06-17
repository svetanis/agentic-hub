# Story Agent Module

## Overview
The Story Module demonstrates the use of a **Sequential Agent Pipeline** combined with an **Iterative Refinement Loop**. It is designed to autonomously generate, critique, and refine creative stories based on user prompts.

## How It Works

The `StoryRootAgent` orchestrates a sequence of specialized agents:

1. **Drafting Agent**: Takes the user's initial prompt and creates a first draft of the story.
2. **Critique Agent**: Reviews the draft for narrative flow, character development, and engagement. If the story is perfect, it outputs "APPROVED".
3. **Refinement Agent**: Takes the critique feedback and the current draft to produce an improved version.

This loop between Critique and Refinement continues until the story reaches the required quality threshold or a maximum number of iterations is hit.

## Running the Project

To start the Story agent server:

```bash
export GROQ_API_KEY="YOUR_GROQ_API_KEY"
mvn compile exec:java -Dexec.mainClass="com.svetanis.agents.MultiAgentsSystem"
```

Once running, you can ask the agent to:
- "Write a sci-fi story about a rogue AI learning empathy"

## Configuration
Agent behaviors are configured using YAML files located in `src/main/resources/agent-configs/story/`. All agents utilize the Groq Llama 3.3 70B Versatile model by default.
