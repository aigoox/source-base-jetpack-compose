package com.intelin.core.library.repository.interfaces

import com.intelin.core.library.repository.model.SessionModel

interface ISessionManager {
    fun getAccessToken(): String
    fun setAccessToken(token: String)
    fun getRefreshToken(): String
    fun getRefreshTokenExpired(): Long
    fun getAccessTokenExpired(): Long
    fun setRefreshToken(token: String)
    fun setSession(input: SessionModel)
    fun clear()
}