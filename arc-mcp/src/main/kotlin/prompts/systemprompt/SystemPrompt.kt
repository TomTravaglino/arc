package org.eclipse.lmos.arc.mcp.prompts.systemprompt

import io.modelcontextprotocol.kotlin.sdk.server.RegisteredPrompt
import io.modelcontextprotocol.kotlin.sdk.types.*

class SystemPrompt {

    fun createSystemPrompt(): RegisteredPrompt {
        val prompt = Prompt(
            name = "Python Developer",
            description = "Develop small Python applications",
            arguments = listOf(
                PromptArgument(
                    name = "Project Name",
                    description = "Project name for the new Python project",
                    required = true,
                ),
            ),
        )

        val promptProvider: suspend (GetPromptRequest) -> GetPromptResult = { request ->
            GetPromptResult(
                messages = listOf(
                    PromptMessage(
                        role = Role.User,
                        content = TextContent(
                            "Develop a Python project named <name>${request.arguments?.get("Project Name")}</name>",
                        ),
                    ),
                ),
                description = "Description for ${request.name}",
            )
        }

        return RegisteredPrompt(prompt, promptProvider)
    }

}
