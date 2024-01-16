package com.intelin.core.network.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile (
    val userId: String,
    val name: String,
    val phone: String,
    val dob: Long,
    val gender: Int,
    val email: String,
    val status: Int,
    val isDeleted: Int,
    val createdAt: Long,
    val createdBy: String,
    val modifiedAt: Long,
    val modifiedBy: String,
    val loginFailedAttempts: Int,
    val nationalID: String,
    val nationalIDType: Int,
    val nationalIDFront: String,
    val nationalIDBack: String,
    val contactAddress: String,
    val isDistributor: Int,
    val isBrand: Int,
    val isRetailer: Int,
    val isApproved: Int,
    val isStaff: Int,
    val lastLogin: Long
) : Parcelable