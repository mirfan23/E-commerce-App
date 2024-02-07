package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant


@Entity(tableName = Constant.tableWishListName)
data class WishListEntity (
    @PrimaryKey
    @ColumnInfo(name = "productId")
    val productId: String = "",
)