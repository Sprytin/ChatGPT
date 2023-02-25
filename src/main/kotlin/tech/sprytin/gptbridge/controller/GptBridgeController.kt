package tech.sprytin.gptbridge.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.sprytin.gptbridge.dto.AskRequest
import tech.sprytin.gptbridge.service.BridgeService
import java.util.*

@RestController
@RequestMapping("/api/v1")
class GptBridgeController(
    val bridgeService: BridgeService
) {
    @PostMapping("/ask")
    suspend fun ask(@RequestBody request: AskRequest) = bridgeService.ask(request)

    @PostMapping("/ask/stream")
    suspend fun askStream(@RequestBody request: AskRequest) = bridgeService.askStream(request)
}

