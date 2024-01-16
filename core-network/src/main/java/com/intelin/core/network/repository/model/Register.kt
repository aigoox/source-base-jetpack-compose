package com.intelin.core.network.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
data class InputInfoRegister(
    var token: String = "",
    var gender: Int = 1,
    var name: String = "",
    var dob: Long? = Calendar.getInstance().timeInMillis / 1000,
    var email: String = "",
    var password: String = "",
    var nationalID: String = "",
    var nationalIDType: String = "1",
    var nationalIDFront: String = "",
    var nationalIDBack: String = "",
    var contactAddress: String = "",
    var retailer: InputRetailer = InputRetailer()

) : Parcelable
@Parcelize
data class InputRetailer (
    var name: String = "",
    var taxNumber: String = "",
    var classification: String = "",
    var license: String = "",
    var address: String = "",
    var cover: String = "",
    var photo: String = ""
) : Parcelable