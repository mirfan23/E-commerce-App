package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class ProfileResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: ProfileData = ProfileData(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class ProfileData(
        @SerializedName("userImage")
        val userImage: String = "",
        @SerializedName("userName")
        val userName: String = ""
    ) : Parcelable
}