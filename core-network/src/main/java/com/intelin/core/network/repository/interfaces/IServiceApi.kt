package com.intelin.core.network.repository.interfaces

import com.intelin.core.network.model.ResponseBody
import com.intelin.core.network.repository.model.InputForgotPassword
import com.intelin.core.network.repository.model.InputInfoRegister
import com.intelin.core.network.repository.model.InputLogin
import com.intelin.core.network.repository.model.InputOtp
import com.intelin.core.network.repository.model.InputOtpResend
import com.intelin.core.network.repository.model.Login
import com.intelin.core.network.repository.model.Otp
import com.intelin.core.network.repository.model.OtpResponse
import com.intelin.core.network.repository.model.Profile
import com.intelin.core.network.utils.base.Urls
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface IServiceApi {

    @POST(Urls.LOGIN)
    suspend fun login(
        @Body input: InputLogin
    ): ResponseBody<Otp>

    @PUT(Urls.LOGIN)
    suspend fun submitOtpLogin(
        @Body input: InputOtp
    ): ResponseBody<Login>

    @GET(Urls.PROFILE)
    suspend fun profile(): ResponseBody<Profile>

    @GET(Urls.OTP)
    suspend fun phoneRegister(
        @Query("phone") phone: String
    ): ResponseBody<Otp>

    @PUT(Urls.OTP)
    suspend fun submitOtp(
        @Body input: InputOtp
    ): ResponseBody<OtpResponse>

    @HTTP(method = "DELETE", path = Urls.OTP, hasBody = true)
    suspend fun resendOTP(
        @Body input: InputOtpResend
    ): ResponseBody<Otp>

    @POST(Urls.ACCOUNT_REGISTER)
    suspend fun register(
        @Body input: InputInfoRegister
    ): ResponseBody<Login>

    @GET(Urls.PASSWORD)
    suspend fun forgotPasswordPhone(
        @Query("phone") input: String
    ): ResponseBody<Otp>

    @GET(Urls.FORGOT)
    suspend fun forgotPasswordEmail(
        @Query("email") input: String
    ): ResponseBody<Any>

    @POST(Urls.PASSWORD)
    suspend fun forgotPassword(
        @Body input: InputForgotPassword
    ): ResponseBody<Any>

}