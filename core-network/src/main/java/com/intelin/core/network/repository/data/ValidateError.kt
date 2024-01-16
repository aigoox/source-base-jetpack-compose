package com.intelin.core.network.repository.data

sealed class ValidateError(val code: String, val message: String) {
    object ACCOUNT_EXISTED_ERROR: ValidateError("COMMON_4002", "Account has existed error")
    object COMMON_NOTFOUND_ERROR: ValidateError("COMMON_4000", "Not found error")
    object COMMON_DUPLICATE_ERROR: ValidateError("COMMON_4001", "Duplicate error")
    object BAD_REQUEST: ValidateError("AUTH_4001", "Bad request, validation error")
    object AUTHENTICATION_TOKEN_NOT_FOUND: ValidateError("4000", "Token not found when authenticate")
    object AUTHENTICATION_TOKEN_REQUIRE_REFRESH: ValidateError("4001", "Token require to refresh when authenticate")
    object AUTHENTICATION_TOKEN_EXPIRE_BY_TIMEOUT: ValidateError("4002", "Token expire by timeout when authenticate")
    object WRONG_USERNAME_PASSWORD: ValidateError("LOGIN_4000", "Wrong username or password")
    object ACCOUNT_LOCKED: ValidateError("LOGIN_4002", "Account locked")
    object FORBIDDEN_ERROR: ValidateError("4003", "Can't access this url and method")
    object OTP_NOT_MATCH: ValidateError("OTP_4000", "Otp not match")
    object OTP_CODE_EXPIRED: ValidateError("OTP_4001", "Otp was expired")
    object OTP_TRANSACTION_FAILED: ValidateError("OTP_4002", "Otp transaction was failed")
    object OTP_WRONG_MANY_TIME: ValidateError("OTP_4003", "Otp wrong many time")
    object REQUEST_OTP_LIMIT: ValidateError("OTP_4004", "Request otp limited")
    object TOKEN_REGISTER_EXPIRED: ValidateError("REGISTER_4000", "Token register expired")
    object TOKEN_FORGOT_PASSWORD_EXPIRED: ValidateError("PASSWORD_4000", "Token forgot password expired")
    object DUPLICATE_PHONE: ValidateError("DUPLICATE_4000", "Duplicate phone")
    object ACCOUNT_NOT_ACTIVATED: ValidateError("LOGIN_4001", "Account not activated")
    object WRONG_PASSWORD: ValidateError("PASSWORD_4000", "Wrong password")
}
