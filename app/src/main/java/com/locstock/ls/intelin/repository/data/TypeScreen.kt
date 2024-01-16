package com.locstock.ls.intelin.repository.data

sealed class TypeRegisterScreen(val name: String, val value: Int) {
    object PHONE: TypeRegisterScreen("PHONE", 0)
    object OTP: TypeRegisterScreen("OTP", 1)
    object REGISTER: TypeRegisterScreen("REGISTER", 2)
    object DONE: TypeRegisterScreen("DONE", -1)
}

enum class EnumLoginScreen {
    LOGIN,
    OTP
}