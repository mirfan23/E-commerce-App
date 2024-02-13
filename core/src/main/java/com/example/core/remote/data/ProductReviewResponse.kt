package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class ProductReviewResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: List<ProductReviewData> = listOf(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class ProductReviewData(
        @SerializedName("userImage")
        val userImage: String = "",
        @SerializedName("userName")
        val userName: String = "",
        @SerializedName("userRating")
        val userRating: Int = 0,
        @SerializedName("userReview")
        val userReview: String = ""
    ) : Parcelable
}