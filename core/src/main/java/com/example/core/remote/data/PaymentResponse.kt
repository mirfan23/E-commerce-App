package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class PaymentResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: List<DataPayment> = listOf(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class DataPayment(
        @SerializedName("item")
        val item: List<Item> = listOf(),
        @SerializedName("title")
        val title: String = ""
    ) : Parcelable {
        @Keep
        @Parcelize
        data class Item(
            @SerializedName("image")
            val image: String = "",
            @SerializedName("label")
            val label: String = "",
            @SerializedName("status")
            val status: Boolean = false
        ) : Parcelable
    }
}