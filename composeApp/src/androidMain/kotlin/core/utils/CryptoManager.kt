package core.utils

import android.annotation.SuppressLint
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

actual class CryptoManager actual constructor() {
    @SuppressLint("GetInstance")
    actual fun decrypt(input: String): String {
        val cipher = Cipher.getInstance("DES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(CRYPTO_MANAGER_KEY.toByteArray(), "DES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
        val encryptedBytes = Base64.decode(input, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }
}
