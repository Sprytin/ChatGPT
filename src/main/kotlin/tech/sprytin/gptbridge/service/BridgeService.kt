package tech.sprytin.gptbridge.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.reactive.awaitLast
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import tech.sprytin.gptbridge.dto.*

@Service
class BridgeService(
    val proxyWebClient: WebClient,
    val tokenService: TokenService
) {
    suspend fun ask(request: AskRequest): AskResponse {
        return askStream(request).awaitLast()
    }

    suspend fun askStream(request: AskRequest): Flux<AskResponse> {
        val token = awaitToken()

        return proxyWebClient.post()
            .bodyValue(request.toChatGPTRequest())
            .header(HttpHeaders.AUTHORIZATION, token.value)
            .retrieve()
            .bodyToFlux(GptResponse::class.java)
            //Ignore [DONE] class cast error
            .onErrorContinue { _, _ -> }
            //Filter only chatGPT messages
            .filter { it.message.author.role == "assistant" }
            .map { it.toAskResponse() }
            .doFinally { tokenService.markTokenAsFree(token) }
    }


    private suspend fun awaitToken(): Token {
        while (true) {
            val token = tokenService.getToken()
            if (token != null) {
                return token
            }
            delay(100)
        }
    }
}

fun AskRequest.toChatGPTRequest() = ChatRequest(
    conversationId = conversationId,
    parentMessageId = parentMessageId,
    messages = listOf(
        Message(
            role = "user",
            content = Content("text", listOf(message))
        )
    )
)
