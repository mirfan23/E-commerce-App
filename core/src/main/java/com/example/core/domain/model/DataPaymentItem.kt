package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataPaymentItem (
    var image: String = "",
    var label: String = "",
    var status: Boolean = false
): Parcelable