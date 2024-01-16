package com.locstock.ls.intelin.repository.model

object CustomerUser {
    var userId: String? = null
    var name: String? = null
    var phone: String? = null
    var dob: Long? = null
    var gender: Int? = null
    var email: String? = null
    var status: Int? = null
    var isDeleted: Int? = null
    var createdAt: Long? = null
    var createdBy: String? = null
    var modifiedAt: Long? = null
    var modifiedBy: String? = null
    var loginFailedAttempts: Int? = null
    var nationalID: String? = null
    var nationalIDType: Int? = null
    var nationalIDFront: String? = null
    var nationalIDBack: String? = null
    var contactAddress: String? = null
    var isDistributor: Int? = null
    var isBrand: Int? = null
    var isRetailer: Int? = null
    var isApproved: Int? = null
    var isStaff: Int? = null
    var lastLogin: Long? = null
}