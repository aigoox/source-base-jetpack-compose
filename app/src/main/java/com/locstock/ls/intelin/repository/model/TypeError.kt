package com.locstock.ls.intelin.repository.model

data class TypeErrorRegister(var yourName: String = "", var password: String = "", var nationalId: String = "")

data class TypeErrorForgotPassword(var phone: String = "", var password: String = "", var confirmPassword: String = "")