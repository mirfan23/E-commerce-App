package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class RefreshTokenResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: RefreshTokenData,
    @SerializedName("message")
    val message: String
) : Parcelable {
    @Keep
    @Parcelize
    data class RefreshTokenData(
        @SerializedName("accessToken")
        val accessToken: String,
        @SerializedName("expiresAt")
        val expiresAt: Int,
        @SerializedName("refreshToken")
        val refreshToken: String
    ) : Parcelable
}