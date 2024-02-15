package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataDetailProduct(
    var brand: String = "",
    var description: String = "",
    var image: List<String> = listOf(),
    var productId: String = "",
    var productName: String = "",
    var productPrice: Int = 0,
    var productRating: Double = 0.0,
    var productVariant: List<DataDetailVariantProduct> = listOf(),
    var sale: Int = 0,
    var stock: Int = 0,
    var totalRating: Int = 0,
    var totalReview: Int = 0,
    var totalSatisfaction: Int = 0
): Parcelable
