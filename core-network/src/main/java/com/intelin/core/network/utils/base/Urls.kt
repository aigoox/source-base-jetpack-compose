package com.intelin.core.network.utils.base

class Urls {
    companion object {
        private const val API = "lsUser/"
        const val LOGIN = API  + "login"
        const val PROFILE = API  + "profile"
        const val OTP = API  + "otp"
        const val PASSWORD = API  + "password"
        const val FORGOT = API  + "forgot/email"
        const val ACCOUNT_REGISTER = API  + "register"
    }
}