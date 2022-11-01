package com.ci.act.util.secure

import android.util.Base64
import com.ci.act.BuildConfig
import com.google.gson.GsonBuilder
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class AES {

    companion object {
        private const val algorithm = "AES/CBC/PKCS5PADDING"
        private val iv = IvParameterSpec(BuildConfig.aes_iv.toByteArray(charset("UTF-8")))
        private val skeySpec =
            SecretKeySpec(BuildConfig.aes_secret_key.toByteArray(charset("UTF-8")), "AES")
        private val gson = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()

        /**
         * This function is used to encrypt data
         * @param message
         * @return encrypted data
         */
        fun encrypt(message: String?): String? {
            if (message == null || message.trim().isEmpty()) {
                return null
            }
            try {
                val cipher = Cipher.getInstance(algorithm)
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
                val encrypted = cipher.doFinal(message.toByteArray())
                return Base64.encodeToString(encrypted, Base64.NO_WRAP or Base64.DEFAULT)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * This function is used to decrypt data
         * @param encrypted
         * @return decrypted data
         */
        fun decrypt(encrypted: String?): String? {
            if (encrypted == null || encrypted.trim().isEmpty()) {
                return null
            }
            try {
                val cipher = Cipher.getInstance(algorithm)
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
                return String(
                    cipher.doFinal(
                        Base64.decode(
                            encrypted,
                            Base64.NO_WRAP or Base64.DEFAULT
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }


        fun toJsonEncryptedString(requestModel: Any?): String? {
            if (requestModel == null) {
                return null
            }
            val jsonString = gson.toJson(requestModel, requestModel.javaClass)
            return encrypt(jsonString)


        }

        fun <T> fromJsonEncryptedString(encryptedString: String?, dataClass: Class<T>?): T? {
            if (encryptedString == null || dataClass == null) {
                return null
            }
            val decrypted = decrypt(encryptedString)
            return gson.fromJson(decrypted, dataClass)

        }
    }


}