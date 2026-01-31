package com.patatus.patatuswallet.features.crypto.di

import com.patatus.patatuswallet.core.di.AppContainer
import com.patatus.patatuswallet.features.crypto.domain.usecases.GetCoinsUseCase
import com.patatus.patatuswallet.features.crypto.presentation.viewmodels.CryptoViewModelFactory

class CryptoModule(
    private val appContainer: AppContainer) {

    private fun provideGetCoinsUseCase(): GetCoinsUseCase {
        return GetCoinsUseCase(appContainer.cryptoRepository)
    }
    fun provideCoinsViewModelFactory(): CryptoViewModelFactory {
        return CryptoViewModelFactory(
            getCoinsUseCase = provideGetCoinsUseCase()
        )
    }
}