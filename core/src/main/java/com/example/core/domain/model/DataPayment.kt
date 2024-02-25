package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class DataPayment (
    var item: List<DataPaymentItem> = listOf(),
    val title: String = ""
): Parcelable