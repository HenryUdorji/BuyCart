package com.hashconcepts.buycart.di

import com.hashconcepts.buycart.data.repository.AuthRepositoryImpl
import com.hashconcepts.buycart.data.repository.CartRepositoryImpl
import com.hashconcepts.buycart.data.repository.ProductsRepositoryImpl
import com.hashconcepts.buycart.domain.repository.AuthRepository
import com.hashconcepts.buycart.domain.repository.CartRepository
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @created 23/06/2022 - 9:38 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsProductRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    @Singleton
    abstract fun bindsCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository
}