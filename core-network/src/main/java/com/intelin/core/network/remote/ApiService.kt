package com.intelin.core.network.remote

import com.intelin.core.library.repository.interfaces.IRsa
import com.intelin.core.network.repository.interfaces.IServiceApi
import com.intelin.core.network.repository.model.InputForgotPassword
import com.intelin.core.network.repository.model.InputInfoRegister
import com.intelin.core.network.repository.model.InputLogin
import com.intelin.core.network.repository.model.InputOtp
import com.intelin.core.network.repository.model.InputOtpResend
import com.intelin.core.network.utils.extension.performNetworkCall

class ApiService(private val service: IServiceApi, private val rsa: IRsa) {

    fun login(input: InputLogin) = performNetworkCall {
        return@performNetworkCall service.login(input.copy(password = rsa.generateEncryptPassword(input.password)))
    }

    fun register(input: InputInfoRegister) = performNetworkCall {
        return@performNetworkCall service.register(input.copy(password = rsa.generateEncryptPassword(input.password)))
    }

    fun profile() = performNetworkCall {
        return@performNetworkCall service.profile()
    }

    fun phoneRegister(phone: String) = performNetworkCall {
        return@performNetworkCall service.phoneRegister(phone)
    }

    fun submitOtp(otpKey: String, code: String) = performNetworkCall {
        return@performNetworkCall service.submitOtp(InputOtp(otpKey, code))
    }

    fun resendOTP(otpKey: String) = performNetworkCall {
        return@performNetworkCall service.resendOTP(InputOtpResend(otpKey))
    }

    fun forgotPasswordPhone(phone: String) = performNetworkCall {
        return@performNetworkCall service.forgotPasswordPhone(phone)
    }

    fun forgotPasswordEmail(email: String) = performNetworkCall {
        return@performNetworkCall service.forgotPasswordEmail(email)
    }

    fun forgotPasswordPhone(token: String?, password: String) = performNetworkCall {
        return@performNetworkCall service.forgotPassword(InputForgotPassword(token, rsa.generateEncryptPassword(password)))
    }

    fun submitOtpLogin(token: String?, code: String) = performNetworkCall {
        return@performNetworkCall service.submitOtpLogin(InputOtp(token, code))
    }
}