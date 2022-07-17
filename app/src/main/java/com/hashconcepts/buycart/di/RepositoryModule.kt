package com.hashconcepts.buycart.di

import com.hashconcepts.buycart.data.repository.*
import com.hashconcepts.buycart.domain.repository.*
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

    @Binds
    @ViewModelScoped
    abstract fun bindsProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}