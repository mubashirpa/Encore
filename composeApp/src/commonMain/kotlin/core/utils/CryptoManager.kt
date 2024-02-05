package core.utils

expect class CryptoManager() {
    fun decrypt(input: String): String
}

const val CRYPTO_MANAGER_KEY = "38346591"
