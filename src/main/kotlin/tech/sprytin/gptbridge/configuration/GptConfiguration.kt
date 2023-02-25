package tech.sprytin.gptbridge.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(BridgeProperties::class)
class GptConfiguration(
    val bridgeProperties: BridgeProperties
) {

    @Bean
    fun proxyWebClient() = WebClient.builder().baseUrl(bridgeProperties.proxyUrl).build()
}