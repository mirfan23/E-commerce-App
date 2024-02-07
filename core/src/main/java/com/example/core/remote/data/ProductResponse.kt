package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class ProductResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: ProductData = ProductData(),
    @SerializedName("message")
    val message: String = ""
) : Parcelable {
    @Keep
    @Parcelize
    data class ProductData(
        @SerializedName("currentItemCount")
        val currentItemCount: Int = 0,
        @SerializedName("items")
        val items: List<ProductItem> = listOf(),
        @SerializedName("itemsPerPage")
        val itemsPerPage: Int = 0,
        @SerializedName("pageIndex")
        val pageIndex: Int = 0,
        @SerializedName("totalPages")
        val totalPages: Int = 0
    ) : Parcelable {
        @Keep
        @Parcelize
        data class ProductItem(
            @SerializedName("brand")
            val brand: String = "",
            @SerializedName("image")
            val image: String = "",
            @SerializedName("productId")
            val productId: String = "",
            @SerializedName("productName")
            val productName: String,
            @SerializedName("productPrice")
            val productPrice: Int = 0,
            @SerializedName("productRating")
            val productRating: Double = 0.0,
            @SerializedName("sale")
            val sale: Int = 0,
            @SerializedName("store")
            val store: String = ""
        ) : Parcelable
    }
}