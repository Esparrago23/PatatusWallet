package com.patatus.patatuswallet.features.crypto.domain.usecases

import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin
import com.patatus.patatuswallet.features.crypto.domain.repositories.CryptoRepository


class GetCoinsUseCase(
    private val repository: CryptoRepository
) {

    suspend operator fun invoke(): Result<List<CryptoCoin>> {
        return try {
            val coins = repository.getCoins()
            Result.success(coins)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}