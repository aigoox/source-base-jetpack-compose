package com.locstock.ls.intelin.repository.interfaces

interface IPropsLogin {
    fun onUsername(data: String)
    fun onPassword(data: String)
    fun onStartRegister()
    fun onForgotPassword()
    fun onSubmit()
}