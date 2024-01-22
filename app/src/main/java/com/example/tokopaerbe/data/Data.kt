package com.example.tokopaerbe.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Data(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("expiresAt")
    val expiresAt: Int,
    @SerializedName("refreshToken")
    val refreshToken: String
)