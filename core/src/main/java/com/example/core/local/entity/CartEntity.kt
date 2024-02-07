package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant


@Entity(tableName = Constant.tableCartName)
data class CartEntity (
    @PrimaryKey
    @ColumnInfo(name = "productId")
    val productId: String = ""
)