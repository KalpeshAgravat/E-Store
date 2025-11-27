package com.andy.fakestore.core.di

import android.app.Application
import androidx.room.Room
import com.andy.fakestore.core.data.local.AppDatabase
import com.andy.fakestore.feature_cart.data.local.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "fakestore_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCartDao(db: AppDatabase): CartDao {
        return db.cartDao()
    }
}
