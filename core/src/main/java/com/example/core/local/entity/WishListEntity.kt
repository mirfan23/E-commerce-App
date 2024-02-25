package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant


@Entity(tableName = Constant.tableWishListName)
data class WishListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wishlistId")
    val wishlistId: Int = 0,
    @ColumnInfo(name = "productId")
    val productId: String = "",
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "productName")
    val productName: String = "",
    @ColumnInfo(name = "productPrice")
    val productPrice: Int = 0,
    @ColumnInfo(name = "productRating")
    val productRating: Double = 0.0,
    @ColumnInfo(name = "sale")
    val sale: Int = 0,
    @ColumnInfo(name = "store")
    val store: String = "",
    @ColumnInfo(name = "userId")
    val userId: String = "",
    @ColumnInfo(name = "stock")
    val stock: Int = 0,
    @ColumnInfo(name = "variant")
    val variant: String = "",
    @ColumnInfo(name = "wishlist")
    val wishlist: Boolean = false,
)