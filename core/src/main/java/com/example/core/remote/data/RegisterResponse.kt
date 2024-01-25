package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class RegisterResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: RegisterData = RegisterData(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class RegisterData(
        @SerializedName("accessToken")
        val accessToken: String ="",
        @SerializedName("expiresAt")
        val expiresAt: Int = 0,
        @SerializedName("refreshToken")
        val refreshToken: String = ""
    ) : Parcelable
}