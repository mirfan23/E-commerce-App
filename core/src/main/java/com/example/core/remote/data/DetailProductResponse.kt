package com.example.core.remote.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class DetailProductResponse(
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
        @SerializedName("brand")
        val brand: String = "",
        @SerializedName("description")
        val description: String = "",
        @SerializedName("image")
        val image: List<String> = listOf(),
        @SerializedName("productId")
        val productId: String = "",
        @SerializedName("productName")
        val productName: String ="",
        @SerializedName("productPrice")
        val productPrice: Int = 0,
        @SerializedName("productRating")
        val productRating: Double = 0.0,
        @SerializedName("productVariant")
        val productVariant: List<ProductVariant> = listOf(),
        @SerializedName("sale")
        val sale: Int = 0,
        @SerializedName("stock")
        val stock: Int = 0,
        @SerializedName("store")
        val store: String = "",
        @SerializedName("totalRating")
        val totalRating: Int = 0,
        @SerializedName("totalReview")
        val totalReview: Int = 0,
        @SerializedName("totalSatisfaction")
        val totalSatisfaction: Int = 0
    ) : Parcelable {
        @Keep
        @Parcelize
        data class ProductVariant(
            @SerializedName("variantName")
            val variantName: String = "",
            @SerializedName("variantPrice")
            val variantPrice: Int = 0
        ) : Parcelable
    }
}