package com.locstock.ls.intelin.repository.interfaces

import androidx.compose.runtime.Composable
import com.intelin.core.design.repository.interfaces.IPropsOTPScreen
import com.locstock.ls.intelin.repository.data.EnumScreenForgotPassword
import com.locstock.ls.intelin.repository.model.TypeErrorForgotPassword
import kotlinx.coroutines.flow.MutableStateFlow

interface IPropsForgotPassword: IPropsOTPScreen {
    fun onEmailOrPhone(data: String)
    fun onPassword(data: String)
    fun onConfirmPassword(data: String)
    fun onStartLogin()
    @Composable
    fun messageError(): TypeErrorForgotPassword
    @Composable
    fun messageNotify(): MutableStateFlow<String>
    @Composable
    fun screen(): EnumScreenForgotPassword
    @Composable
    fun loading(): MutableStateFlow<Boolean>
    fun messageResult(): String
    fun onSubmit()
}