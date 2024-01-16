package com.intelin.core.design.repository.interfaces

import androidx.compose.runtime.Composable

interface IPropsOTPScreen {
    fun onChangeOtp(data: String)
    fun handleResendOtp()

    @Composable
    fun phone(): String
}