package vn.namnp.cryptocurrencyapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import vn.namnp.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinApi {

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>
}