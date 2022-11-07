package vn.namnp.cryptocurrencyapp.domain.repository

import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoin(coinId: String): CoinDetailDto

    suspend fun getCoins(): List<CoinDto>
}