package com.andy.fakestore.feature_cart.di

import com.andy.fakestore.feature_cart.data.repository.CartRepositoryImpl
import com.andy.fakestore.feature_cart.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}
