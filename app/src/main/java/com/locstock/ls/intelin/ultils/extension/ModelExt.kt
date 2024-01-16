package com.locstock.ls.intelin.ultils.extension

import com.intelin.core.library.ultils.AppUtils
import com.intelin.core.network.repository.model.Profile
import com.locstock.ls.intelin.repository.model.CustomerUser

fun Map<String, Any?>.isHasField(field: String): Boolean {
    this.map {
        if (field == it.key) {
            return true
        }
    }
    return false
}

fun CustomerUser.toJson(): String {
    return AppUtils.gson.toJson(this.toString())
}

fun CustomerUser.setData(data: Profile) {
    this.apply {
        userId = data.userId
        name = data.name
        phone = data.phone
        dob = data.dob
        gender = data.gender
        email = data.email
        status = data.status
        isDeleted = data.isDeleted
        createdAt = data.createdAt
        createdBy = data.createdBy
        loginFailedAttempts = data.loginFailedAttempts
        nationalID = data.nationalID
        nationalIDType = data.nationalIDType
        nationalIDFront = data.nationalIDFront
        nationalIDBack = data.nationalIDBack
        contactAddress = data.contactAddress
        isDistributor = data.isDistributor
        isBrand = data.isBrand
        isRetailer = data.isRetailer
        isApproved = data.isApproved
        isStaff = data.isStaff
        lastLogin = data.lastLogin
    }
}