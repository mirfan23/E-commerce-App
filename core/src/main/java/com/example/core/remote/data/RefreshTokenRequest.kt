package com.example.core.remote.data

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class RefreshTokenRequest(
    @SerializedName("token")
    val token: String?
) : Parcelable