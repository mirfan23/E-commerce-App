package com.example.core.utils

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.state.SplashState
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProductResponse
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

    fun ProductResponse.ProductData.ProductItem.toUIData() = DataProduct(
        id = productId,
        name = productName,
        image = image,
        store = store,
        price = productPrice,
        rating = productRating,
        sale = sale
    )
    fun ProductResponse.toUIListData() = data.items.map { item -> item.toUIData() }.toList()

    fun DataLogin.toDataToken() = DataToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiresAt = expiresAt
    )

    fun DataLogin.toProfileName() = DataProfile(
        userName = userName,
    )

    fun Triple<String, String, Boolean>.toUIData() = DataSession(
        name = this.first,
        accessToken = this.second,
        onBoardingState = this.third
    )

    fun DataSession.toSplashState() = when {
        this.name.isEmpty() && this.accessToken.isNullOrEmpty().not() -> {
            SplashState.Profile
        }

        this.name.isNullOrEmpty().not() && this.accessToken.isNullOrEmpty().not() -> {
            SplashState.Dashboard
        }

        this.onBoardingState -> {
            SplashState.Login
        }

        else -> {
            SplashState.OnBoarding
        }
    }
}