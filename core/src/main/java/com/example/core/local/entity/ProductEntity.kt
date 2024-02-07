package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant

@Entity(tableName = Constant.tableProductName)
data class ProductEntity(
    @PrimaryKey
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
    val  sale: Int = 0,
    @ColumnInfo(name = "productRating")
    val store: String = "",
)
