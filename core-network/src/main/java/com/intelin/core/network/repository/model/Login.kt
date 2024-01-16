package com.intelin.core.network.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputLogin(
    val username: String,
    val password: String,
    val device: InputDevice
) : Parcelable

@Parcelize
data class ProfileDetails (
    val cusName: String,
    val status: Int
) : Parcelable

@Parcelize
data class  Login(
    val token: String,
    val expireAt: Long,
    val refreshAt: Long,
    val profileDetails: ProfileDetails
) : Parcelable

@Parcelize
data class InputDevice (
    val deviceProducer: String,
    val deviceModel: String,
    val deviceName: String,
    val deviceDisplayName: String,
    val deviceType: String = "ANDROID",
    val pushKey: String,
    val platformOS: String = "ANDROID",
    val versionOS: Int,
    val applicationVersion: String
) : Parcelable