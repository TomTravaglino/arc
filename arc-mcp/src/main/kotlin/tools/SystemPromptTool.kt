package org.eclipse.lmos.arc.mcp.tools

import io.modelcontextprotocol.kotlin.sdk.server.RegisteredTool
import io.modelcontextprotocol.kotlin.sdk.types.CallToolRequest
import io.modelcontextprotocol.kotlin.sdk.types.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.types.TextContent
import io.modelcontextprotocol.kotlin.sdk.types.Tool
import io.modelcontextprotocol.kotlin.sdk.types.ToolSchema
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SystemPromptTool {
    companion object {
        private const val USE_CASE_PARAM = "useCase"
    }

    fun createAdlSystemPromptTool(): RegisteredTool {
        val adlSystemPromptTool = Tool(
            name = "get-system-prompt",
            description = "Retrieves the system prompt for a given use case",
            inputSchema = ToolSchema(
                properties = buildJsonObject {
                    put(USE_CASE_PARAM, buildJsonObject {
                        put("type", "string")
                        put("description", "The use case for which to retrieve the system prompt")
                    })
                },
                required = listOf(USE_CASE_PARAM),
            ),
        )

        val handler: suspend (CallToolRequest) -> CallToolResult = { request ->
            val useCase = try {
                val argValue = request.arguments?.get(USE_CASE_PARAM)?.toString() ?: "unknown"
                argValue
            } catch (_: Exception) {
                "unknown"
            }
            CallToolResult(
                content = listOf(TextContent("Received use_case: $useCase")),
                isError = false
            )
        }

        return RegisteredTool(adlSystemPromptTool, handler)
    }
}