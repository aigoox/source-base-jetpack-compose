package com.intelin.core.design.repository.interfaces

interface IPropsRegisterAccountScreen {
    fun onYourName(data: String)
    fun onPassword(data: String)
    fun onGender(data: Int)
    fun onNationalID(data: String)
    fun onEmail(data: String)
    fun onBirthDay(data: Long)
    fun onAddress(data: String)
    fun onReName(data: String)
    fun onReTax(data: String)
    fun onReLicense(data: String)
    fun onReAddress(data: String)
}