package com.intelin.core.network.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputForgotPassword(
    val token: String?,
    val password: String
) : Parcelable