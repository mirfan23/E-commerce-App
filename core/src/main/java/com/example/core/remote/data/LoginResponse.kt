package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class LoginResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: LoginData = LoginData(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class LoginData(
        @SerializedName("accessToken")
        val accessToken: String = "",
        @SerializedName("expiresAt")
        val expiresAt: Int = 0,
        @SerializedName("refreshToken")
        val refreshToken: String = "",
        @SerializedName("userImage")
        val userImage: String = "",
        @SerializedName("userName")
        val userName: String = ""
    ) : Parcelable
}