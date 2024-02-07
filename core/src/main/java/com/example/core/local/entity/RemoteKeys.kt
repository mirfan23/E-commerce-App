package com.example.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant

@Entity(tableName = Constant.remoteKey)
data class RemoteKeys(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("prevKey")
    val prevKey: Int?,
    @ColumnInfo("nextKey")
    val nextKey: Int?
)
