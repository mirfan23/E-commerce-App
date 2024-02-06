package com.example.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.utils.Constant

@Entity(tableName = Constant.remoteKey)
data class RemoteKeys(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
