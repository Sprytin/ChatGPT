package tech.sprytin.gptbridge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GptBridgeApplication

fun main(args: Array<String>) {
    runApplication<GptBridgeApplication>(*args)
}
