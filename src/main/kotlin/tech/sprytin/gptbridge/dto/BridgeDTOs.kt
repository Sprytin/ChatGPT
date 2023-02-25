package tech.sprytin.gptbridge.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class AskRequest(
    val message: String,
    val conversationId: String? = null,
    val parentMessageId: String = UUID.randomUUID().toString()
)

data class AskResponse(
    val answer: String,
    val messageId: String,
    val conversationId: String,
    val error: Any? = null
)

data class Message(val id: String = UUID.randomUUID().toString(), val role: String? = null, val content: Content)
data class Content(@JsonProperty("content_type") val contentType: String, val parts: List<String>)

data class ChatRequest(
    val action: String = "next",
    val messages: List<Message>,
    @JsonProperty("conversation_id")
    val conversationId: String? = null,
    @JsonProperty("parent_message_id")
    val parentMessageId: String,
    val model: String = "text-davinci-002-render-sha"
)

data class MessageResponse(
    val id: String,
    val author: AuthorResponse,
    val content: ContentResponse
)

data class AuthorResponse(
    val role: String
)

data class ContentResponse(
    @JsonProperty("content_type")
    val contentType: String,
    val parts: List<String>
)

data class GptResponse(
    val message: MessageResponse,
    @JsonProperty("conversation_id")
    val conversationId: String,
    val error: Any?
)

fun GptResponse.toAskResponse() =
    AskResponse(
        answer = message.content.parts.first(),
        messageId = message.id,
        conversationId = conversationId
    )