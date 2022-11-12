package vn.namnp.cryptocurrencyapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.data.remote.CoinApi
import vn.namnp.cryptocurrencyapp.data.remote.dto.toCoin
import vn.namnp.cryptocurrencyapp.data.remote.dto.toCoinDetail
import vn.namnp.cryptocurrencyapp.domain.model.Coin
import vn.namnp.cryptocurrencyapp.domain.model.CoinDetail
import vn.namnp.cryptocurrencyapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
): CoinRepository {
    // GOOD
    override suspend fun getCoin(coinId: String): Resource<CoinDetail> {
        // response handling should be done here (data layer)
        try {
            val coin = coinApi.getCoin(coinId).toCoinDetail() // map dto to model here (in data layer)
            return Resource.Success<CoinDetail>(coin)
        }catch (e: HttpException) {
            return Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured")
        }catch (e: IOException) {
            return Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection.")
        }
    }

    // NOT GOOD
    // 1. map dto to model here -> GOOD
    // 2. response handling in use case (domain) -> NOT GOOD
    override suspend fun getCoins(): List<Coin> {
        return coinApi.getCoins().map { it.toCoin() }
    }
}