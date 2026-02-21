package com.patatus.patatuswallet.features.crypto.di

import com.patatus.patatuswallet.features.crypto.data.repositories.CryptoRepositoryImpl
import com.patatus.patatuswallet.features.crypto.domain.repositories.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoModule{
    @Binds
    abstract fun bindGetCoinsRepository(
        postsRepositoryImpl: CryptoRepositoryImpl
    ): CryptoRepository

}