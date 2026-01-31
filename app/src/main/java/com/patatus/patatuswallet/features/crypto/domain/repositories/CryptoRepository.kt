package com.patatus.patatuswallet.features.crypto.domain.repositories

import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin

interface CryptoRepository {
    suspend fun getCoins(): List<CryptoCoin>
}