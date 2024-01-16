package com.intelin.core.library.encryption

import android.content.res.AssetManager
import android.util.Log
import com.google.crypto.tink.subtle.Base64
import com.intelin.core.library.repository.interfaces.IRsa
import com.intelin.core.library.ultils.BCrypt
import java.io.DataInputStream
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class RsaImpl(assets: AssetManager) : IRsa {
    private lateinit var fileBytes: ByteArray

    init {
        getByte(assets)
    }
    override fun generateEncryptPassword(password: String): String {
        val passwordHashBcrypt = hashBcrypt(password)
        val encode = encrypt(passwordHashBcrypt).toString()

        Log.d("AAA","- Hash Bcrypt: $password - $passwordHashBcrypt \n - Hash Password: $encode")
        return encode
    }

    private fun hashBcrypt(strHash: String): String {
        return BCrypt.hashpw(strHash, "\$2a\$12\$fkqHb.cOSu/HMnd7leXe7u")
    }

    private fun encrypt(text: String): String? {
        try { // Encrypt the string using the public key
            val test = text.toByteArray()
            val cipherText = encrypt(test, encrypt(fileBytes))
            return Base64.encodeToString(cipherText, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun md5(cipherText: String): String {
        val md5 = "MD5"
        try { // Create MD5 Hash
            val digest = MessageDigest
                .getInstance(md5)
            digest.update(cipherText.toByteArray())
            val messageDigest = digest.digest()
            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    @Throws(Exception::class)
    private fun encrypt(keyBytes: ByteArray?): PublicKey {
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(spec)
    }

    private fun encrypt(text: ByteArray?, key: PublicKey?): ByteArray? {
        var cipherText: ByteArray? = null
        try {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            cipherText = cipher.doFinal(text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cipherText
    }
    override fun getByte(assets: AssetManager) {
        try {
            val fis = assets.open("public_key.rsa")
            val dis = DataInputStream(fis)
            this.fileBytes = ByteArray(fis.available())
            dis.readFully(fileBytes)
            dis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}