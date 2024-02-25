package com.example.core.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.RemoteKeys
import com.example.core.local.entity.WishListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Query("SELECT * FROM product_table")
    fun retrieveAllProducts(): PagingSource<Int, ProductEntity>

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()

    @Query(
        "SELECT * FROM product_table " +
                "WHERE (:brand IS NULL OR brand = :brand) " +
                "AND (:minPrice IS NULL OR productPrice >= :minPrice) " +
                "AND (:maxPrice IS NULL OR productPrice <= :maxPrice) " +
                "ORDER BY " +
                "CASE WHEN :sort = 'rating' THEN productRating END DESC, " +
                "CASE WHEN :sort = 'sale' THEN sale END DESC, " +
                "CASE WHEN :sort = 'lowestprice' THEN productPrice END ASC, " +
                "CASE WHEN :sort = 'highestprice' THEN productPrice END DESC, " +
                "productId ASC"
    )
    fun getProductsWithFilter(
        brand: String?,
        minPrice: Int?,
        maxPrice: Int?,
        sort: String?
    ): PagingSource<Int, ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_key WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RemoteKeys?

    @Query("DELETE FROM remote_key")
    suspend fun deleteAllKey()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Query("SELECT * FROM cart_table WHERE userId = :id")
    fun retrieveAllCart(id: String): Flow<List<CartEntity>>

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllCart()

    @Delete
    suspend fun deleteCart(cart: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishList(wishList: WishListEntity)

    @Query("SELECT * FROM wishlist_table WHERE userId = :id")
    fun retrieveAllWishList(id: String): Flow<List<WishListEntity>>

    @Query("DELETE FROM wishlist_table")
    suspend fun deleteAllWishList()

    @Delete
    suspend fun deleteWishlist(wishList: WishListEntity)

    @Query("SELECT count(*) FROM wishlist_table WHERE variant = :variant AND productId = :id")
    suspend fun checkFavorite(variant: String ,id: Int): Int

    @Query("UPDATE cart_table SET quantity = :value WHERE cartId = :cartId")
    suspend fun updateQuantity(cartId: Int, value: Int)

    @Query("UPDATE cart_table SET isChecked = :value WHERE cartId = :cartId")
    suspend fun updateCheckCart(cartId: Int, value: Boolean)
}