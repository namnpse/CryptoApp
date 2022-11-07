package vn.namnp.cryptocurrencyapp.data.repository

import vn.namnp.cryptocurrencyapp.data.remote.CoinApi
import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDto
import vn.namnp.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
): CoinRepository {
    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return coinApi.getCoin(coinId)
    }

    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }
}