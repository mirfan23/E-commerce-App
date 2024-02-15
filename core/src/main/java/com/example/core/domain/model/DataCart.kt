package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataCart (
    var productId: String = "",
    var image: String = "",
    var variant: String = "",
    var productName: String = "",
    var productPrice: Int = 0,
    var stock: Int = 0,
    var quantity: Int = 0
): Parcelable