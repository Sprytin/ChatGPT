package tech.sprytin.gptbridge.service

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.springframework.stereotype.Service
import tech.sprytin.gptbridge.configuration.BridgeProperties

@Service
class TokenService(
    properties: BridgeProperties
) {
    private val tokens = properties.tokens.map { Token(it) }
    private val updateMutex = Mutex()

    suspend fun getToken(): Token? {
        updateMutex.withLock {
            val freeToken = tokens
                .filter { !it.inUse }
                .minByOrNull { it.counter }

            if (freeToken != null) {
                freeToken.inUse = true
                freeToken.counter++
                return freeToken
            }
        }
        return null
    }

    fun markTokenAsFree(token: Token) {
        token.inUse = false
    }
}


class Token(val value: String, var inUse: Boolean = false, var counter: Int = 0)
