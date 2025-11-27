package com.andy.fakestore.feature_cart.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andy.fakestore.feature_cart.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: CartEntity)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartEntity>>

    @Query("DELETE FROM cart_items")
    suspend fun deleteAllCartItems()
    
    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getCartItemByProductId(productId: Int): CartEntity?
}
