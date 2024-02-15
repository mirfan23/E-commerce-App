package com.example.core.utils

import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataDetailVariantProduct
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataReviewProduct
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.SplashState
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.WishListEntity
import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProductResponse
import com.example.core.remote.data.ProductReviewResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.utils.DataMapper.toUIListData

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

    fun ProductResponse.ProductData.ProductItem.toLocalData() = ProductEntity(
        productId = productId,
        productName = productName,
        productPrice = productPrice,
        productRating = productRating,
        image = image,
        store = store,
        sale = sale
    )

    fun ProductResponse.toLocalListData() = data.items.map { item -> item.toLocalData() }.toList()

    fun DetailProductResponse.ProductData.toUIData() = DataDetailProduct(
        brand = brand,
        description = description,
        image = image,
        productId = productId,
        productName = productName,
        productPrice = productPrice,
        productRating = productRating,
        productVariant = productVariant.map { variant -> variant.toUIVariantData() },
        sale = sale,
        stock = stock,
        totalRating = totalRating,
        totalReview = totalReview,
        totalSatisfaction = totalSatisfaction
    )

    private fun DetailProductResponse.ProductData.ProductVariant.toUIVariantData() =
        DataDetailVariantProduct(
            variantName = variantName,
            variantPrice = variantPrice
        )

    fun List<ProductReviewResponse.ProductReviewData>.toUIListData() = map { data ->
        data.toUIData()
    }.toList()

    fun ProductReviewResponse.ProductReviewData.toUIData() = DataReviewProduct(
        userImage = userImage,
        userName = userName,
        userRating = userRating,
        userReview = userReview
    )

    fun DataLogin.toDataToken() = DataToken(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiresAt = expiresAt
    )

    fun ProductEntity.toUIData() = DataProduct(
        productId = productId,
        productName = productName,
        productPrice = productPrice,
        productRating = productRating,
        image = image,
        store = store,
        sale = sale
    )

    fun CartEntity.toUIData() = DataCart(
        productId = productId,
        image = image,
        variant = variant,
        productName = productName,
        productPrice = productPrice,
        stock = stock,
        quantity = quantity
    )

    fun DataCart.toEntity() = CartEntity(
        productId = productId,
        image = image,
        variant = variant,
        productName = productName,
        productPrice = productPrice,
        stock = stock,
        quantity = quantity
    )

    fun WishListEntity.toUIData() = DataWishList(
        productId = productId,
        productName = productName,
        productPrice = productPrice,
        productRating = productRating,
        image = image,
        sale = sale,
        store = store
    )

    fun DataWishList.toEntity() = WishListEntity(
        productId = productId,
        productName = productName,
        productPrice = productPrice,
        productRating = productRating,
        image = image,
        sale = sale,
        store = store
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