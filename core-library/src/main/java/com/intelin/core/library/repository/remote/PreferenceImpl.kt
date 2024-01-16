package com.intelin.core.library.repository.remote

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.intelin.core.library.repository.interfaces.IPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PreferenceImpl
@Inject constructor(
    @ApplicationContext val context: Context
): IPreference
{
    private var sharedPreference: SharedPreferences
    private var editor: SharedPreferences.Editor
    companion object {
        const val APP_SP_NAME = "LOCSTOCK"
        const val MASTER_KEY_ALIAS = MasterKey.DEFAULT_MASTER_KEY_ALIAS
        const val MASTER_KEY_SIZE = MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE
        const val COUNTERS_OPTION = "COUNTERS_OPTION"
    }

    init {
        val params = createKeyGenParameterSpec()
        val masterKey = createMasterKey(params)
        sharedPreference = createSharePreference(masterKey)
        editor = sharedPreference.edit()
    }

    private fun createSharePreference(
        masterKey: MasterKey
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            APP_SP_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun <T> getCountersOption(key: String): T {
        val tamp: T? = null
        return when(tamp as T) {
            is Int -> {
                sharedPreference.getInt(key, -1)
            }
            is Long -> {
                sharedPreference.getLong(key, -1)
            }
            is Float -> {
                sharedPreference.getFloat(key, -1f)
            }
            is String -> {
                sharedPreference.getString(key, "")
            }
            else -> {
                sharedPreference.getBoolean(key, false)
            }
        } as T
    }

    override fun <T> setCountersOption(key: String, value: T) {
        when(value) {
            is Int -> {
                editor.putInt(key, value).apply()
            }
            is Long -> {
                editor.putLong(key, value).apply()
            }
            is Float -> {
                editor.putFloat(key, value).apply()
            }
            is String -> {
                editor.putString(key, value).apply()
            }
            else -> {
                editor.putBoolean(key, value as Boolean).apply()
            }
        }
    }

    private fun createKeyGenParameterSpec(): KeyGenParameterSpec {
        return KeyGenParameterSpec.Builder(
            MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MASTER_KEY_SIZE)
            .build()
    }

    private fun createMasterKey(spec: KeyGenParameterSpec): MasterKey {
        return MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
    }

    override fun getPreference(): SharedPreferences {
        return sharedPreference
    }

    override fun getEditor(): SharedPreferences.Editor {
        return editor
    }

    override fun remove(key: String) {
        editor.remove(key).apply()
    }

    override fun clear() {
        editor.clear().apply()
    }
}