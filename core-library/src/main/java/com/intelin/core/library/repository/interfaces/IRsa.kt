package com.intelin.core.library.repository.interfaces

import android.content.res.AssetManager

interface IRsa {
    fun generateEncryptPassword(password: String): String
    fun md5(cipherText: String): String
    fun getByte(assets: AssetManager)
}