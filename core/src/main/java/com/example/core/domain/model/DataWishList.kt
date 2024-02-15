package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataWishList(
    var productId: String = "",
    var productName: String = "",
    var productPrice: Int = 0,
    var image: String = "",
    var productRating: Double = 0.0,
    var sale: Int = 0,
    var store: String = "",
    var userId: String = ""
): Parcelable