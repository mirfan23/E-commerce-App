package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class LoginRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("firebaseToken")
    val firebaseToken: String?,
    @SerializedName("password")
    val password: String?
) : Parcelable