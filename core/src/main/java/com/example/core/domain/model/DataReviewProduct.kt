package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataReviewProduct(
    val userImage: String = "",
    val userName: String = "",
    val userRating: Int = 0,
    val userReview: String = ""
): Parcelable
