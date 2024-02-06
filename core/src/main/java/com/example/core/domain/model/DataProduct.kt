package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataProduct(
    var id: String = "",
    var name: String = "",
    var price: Int = 0,
    var image: String = "",
    var rating: Double = 0.0,
    var sale: Int = 0,
    var store: String = ""
): Parcelable