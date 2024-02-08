package com.example.core.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.RemoteKeys
import com.example.core.local.entity.WishListEntity

@Database(entities = [ProductEntity::class, RemoteKeys::class, WishListEntity::class, CartEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun appDao(): Dao
}