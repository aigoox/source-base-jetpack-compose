package com.intelin.core.library.repository.model

data class SessionModel(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpired: Long,
    val refreshTokenExpored: Long
)
