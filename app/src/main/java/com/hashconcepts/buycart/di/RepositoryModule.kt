package com.hashconcepts.buycart.di

import com.hashconcepts.buycart.data.repository.AuthRepositoryImpl
import com.hashconcepts.buycart.data.repository.CartRepositoryImpl
import com.hashconcepts.buycart.data.repository.ProductsRepositoryImpl
import com.hashconcepts.buycart.data.repository.WishListRepositoryImpl
import com.hashconcepts.buycart.domain.repository.AuthRepository
import com.hashconcepts.buycart.domain.repository.CartRepository
import com.hashconcepts.buycart.domain.repository.ProductsRepository
import com.hashconcepts.buycart.domain.repository.WishListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @created 23/06/2022 - 9:38 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsProductRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsWishListRepository(wishListRepositoryImpl: WishListRepositoryImpl): WishListRepository
}