package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataDetailVariantProduct(
    var variantName: String = "",
    var variantPrice: Int = 0
): Parcelable
