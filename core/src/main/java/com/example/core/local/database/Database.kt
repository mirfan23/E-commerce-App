package com.example.core.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.RemoteKeys
import com.example.core.local.entity.WishListEntity

@Database(entities = [ProductEntity::class, RemoteKeys::class, WishListEntity::class, CartEntity::class], version = 9)
abstract class Database: RoomDatabase() {
    abstract fun appDao(): Dao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE cart_table ADD COLUMN image TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE cart_table ADD COLUMN variant TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE cart_table ADD COLUMN productName TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE cart_table ADD COLUMN productPrice INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE cart_table ADD COLUMN stock INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE cart_table ADD COLUMN quantity INT DEFAULT 0 NOT NULL")

                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN image TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN productName TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN productPrice INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN productRating REAL DEFAULT 0.0 NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN sale INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN store TEXT DEFAULT '' NOT NULL")
            }
        }
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE cart_table ADD COLUMN userId TEXT DEFAULT '' NOT NULL")

                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN userId TEXT DEFAULT '' NOT NULL")
            }
        }
        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN stock INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE wishlist_table ADD COLUMN variant TEXT DEFAULT '' NOT NULL")
            }
        }
        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE new_cart_table")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN cartId INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN image TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN variant TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN productName TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN productPrice INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN stock INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN quantity INT DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN userId TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN productId TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD COLUMN isChecked BOOLEAN DEFAULT FALSE NOT NULL")
                db.execSQL("ALTER TABLE new_cart_table ADD PRIMARY KEY (cartId)")

                db.execSQL("CREATE TABLE new_wishlist_table")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN wishlistId INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN productId TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN userId TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN image TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN productName TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN productPrice INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN productRating REAL DEFAULT 0.0 NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN sale INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN store TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN stock INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD COLUMN variant TEXT DEFAULT '' NOT NULL")
                db.execSQL("ALTER TABLE new_wishlist_table ADD PRIMARY KEY (wishlistId)")

                db.execSQL("DROP TABLE wishlist_table")
                db.execSQL("DROP TABLE cart_table")
                db.execSQL("ALTER TABLE new_wishlist_table RENAME TO wishlist_table")
                db.execSQL("ALTER TABLE new_cart_table RENAME TO cart_table")
            }
        }
        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE cart_table RENAME COLUMN wishlistId TO cartId")
            }
        }
    }
}