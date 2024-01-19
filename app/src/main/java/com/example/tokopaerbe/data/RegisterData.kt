package com.example.tokopaerbe.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisterData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String
)