package com.andy.fakestore.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andy.fakestore.feature_cart.data.local.dao.CartDao
import com.andy.fakestore.feature_cart.data.local.entity.CartEntity

@Database(entities = [CartEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
