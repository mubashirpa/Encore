package core.utils

import io.ktor.utils.io.core.String
import io.ktor.utils.io.core.toByteArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.serialization.Serializable
import platform.CommonCrypto.CCCrypt
import platform.CommonCrypto.CCCryptorStatus
import platform.CommonCrypto.CCKern

// TODO("Fix CryptoManager")
@Serializable
actual class CryptoManager {
    @OptIn(ExperimentalForeignApi::class)
    actual fun decrypt(input: String): String {
        val keyData = CRYPTO_MANAGER_KEY.toByteArray()
        val inputBytes = input.toByteArray()
        val outputBytes = ByteArray(inputBytes.size)
        val bytesDecrypted: CCKern = 0

        val status: CCCryptorStatus =
            CCCrypt(
                operation = 0U, // kCCDecrypt
                algorithm = 0U, // kCCAlgorithmDES
                options = 0U, // kCCOptionECBMode
                key = keyData.refTo(0),
                keyLength = keyData.size,
                iv = null,
                dataIn = inputBytes.refTo(0),
                dataInLength = inputBytes.size,
                dataOut = outputBytes.refTo(0),
                dataOutAvailable = outputBytes.size,
                dataOutMoved = bytesDecrypted.refTo(0),
            )

        return String(outputBytes, 0, bytesDecrypted.toInt())
    }
}
