package com.intelin.core.library.repository.remote

import com.intelin.core.library.repository.interfaces.IPreference
import com.intelin.core.library.repository.interfaces.ISessionManager
import com.intelin.core.library.repository.model.SessionModel

class SessionManagerImpl constructor(
    private val preference: IPreference
): ISessionManager {
    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val REFRESH_TIME = "REFRESH_TIME"
        const val ACCESST_TIME = "ACCESST_TIME"
    }

    private var sharedPreference = preference.getPreference()
    private var editor = preference.getEditor()
    override fun getAccessToken(): String {
        return sharedPreference.getString(ACCESS_TOKEN, "").toString()
    }

    override fun setAccessToken(token: String) {
        editor.putString(ACCESS_TOKEN, token).apply()
    }

    override fun getRefreshToken(): String {
        return sharedPreference.getString(REFRESH_TOKEN, "") ?: ""
    }

    override fun getRefreshTokenExpired(): Long {
        return sharedPreference.getLong(REFRESH_TIME, -1)
    }

    override fun getAccessTokenExpired(): Long {
        return sharedPreference.getLong(ACCESST_TIME, -1)
    }

    override fun setRefreshToken(token: String) {
        editor.putString(REFRESH_TOKEN, token).apply()
    }

    override fun setSession(input: SessionModel) {
        editor.putString(ACCESS_TOKEN, input.accessToken)
        editor.putString(REFRESH_TOKEN, input.refreshToken)
        editor.putLong(ACCESST_TIME, input.accessTokenExpired)
        editor.putLong(REFRESH_TIME, input.refreshTokenExpored)
        editor.apply()
    }

    override fun clear() {
        editor.remove(ACCESS_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.apply()
    }
}