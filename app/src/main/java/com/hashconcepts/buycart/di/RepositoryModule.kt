package com.hashconcepts.buycart.di

import com.hashconcepts.buycart.data.repository.AuthRepositoryImpl
import com.hashconcepts.buycart.domain.repository.AuthRepository
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
}