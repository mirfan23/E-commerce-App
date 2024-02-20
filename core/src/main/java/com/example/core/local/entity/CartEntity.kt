package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant


@Entity(tableName = Constant.tableCartName)
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cartId")
    val cartId: Int = 0,
    @ColumnInfo(name = "productId")
    val productId: String = "",
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "variant")
    val variant: String = "",
    @ColumnInfo(name = "productName")
    val productName: String = "",
    @ColumnInfo(name = "productPrice")
    val productPrice: Int = 0,
    @ColumnInfo(name = "stock")
    val stock: Int = 0,
    @ColumnInfo(name = "quantity")
    val quantity: Int = 0,
    @ColumnInfo(name = "userId")
    val userId: String = "",
    @ColumnInfo(name = "isChecked")
    val isChecked: Boolean = false
)