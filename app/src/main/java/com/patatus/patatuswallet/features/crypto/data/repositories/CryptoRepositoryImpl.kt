package com.patatus.patatuswallet.features.crypto.data.repositories
//se importa la network, mapper, repository y entitie
import com.patatus.patatuswallet.features.crypto.data.datasources.remote.api.CoinGeckoApi
import com.patatus.patatuswallet.features.crypto.data.datasources.remote.mapper.toDomain
import com.patatus.patatuswallet.features.crypto.domain.repositories.CryptoRepository
import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin
import javax.inject.Inject
class CryptoRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
) : CryptoRepository {
    override suspend fun getCoins(): List<CryptoCoin> {
        val response = api.getCoins(
            currency = "mxn",
            order = "market_cap_desc",
            perPage = 20,
            page = 1,
            sparkline = false
        )
        return response.map { dto -> dto.toDomain()}
    }

}