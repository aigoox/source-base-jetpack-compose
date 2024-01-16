package com.intelin.core.network.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputOtp(
    val otpKey: String?,
    val code: String?
) : Parcelable

@Parcelize
data class InputOtpResend(
    val otpKey: String?
) : Parcelable

@Parcelize
data class OtpResponse(
    val token: String?
) : Parcelable

@Parcelize
data class Otp(
    val otpKey: String? = null,
    val length: Int? = null,
    val timeCodeExpire: Int? = null,
    val timeKeyExpire: Int? = null,
    val contact: String? = null,
    val via: Int? = null
) : Parcelable