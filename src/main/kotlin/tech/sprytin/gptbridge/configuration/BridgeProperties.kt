package tech.sprytin.gptbridge.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("bridge")
data class BridgeProperties(
    val proxyUrl: String,
    val tokens: List<String>
)