package core.utils

import kotlinx.serialization.Serializable

@Serializable
expect class CryptoManager() {
    fun decrypt(input: String): String
}

const val CRYPTO_MANAGER_KEY = "38346591"
