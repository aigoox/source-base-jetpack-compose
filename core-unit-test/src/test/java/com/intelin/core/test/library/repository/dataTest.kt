package com.intelin.core.test.library.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTest(
    val paramString: String,
    val paramInt: Int,
    val paramBoolean: Boolean
) : Parcelable
