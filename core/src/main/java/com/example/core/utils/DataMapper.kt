package com.example.core.utils

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse

object DataMapper {

    fun LoginResponse.toUIData() = DataLogin(
        accessToken = data.accessToken,
        expiresAt = data.expiresAt,
        refreshToken = data.refreshToken,
        userName = data.userName,
    )

    fun RegisterResponse.toUIData() = DataToken(
        accessToken = data.accessToken,
        expiresAt = data.expiresAt,
        refreshToken = data.refreshToken
    )

    fun RefreshTokenResponse.toUIData() = DataToken(
        accessToken = data.accessToken,
        expiresAt = data.expiresAt,
        refreshToken = data.refreshToken
    )

    fun ProfileResponse.toUIData() = DataProfile(
        userName = data.userName,
        userImage = data.userImage
    )
}