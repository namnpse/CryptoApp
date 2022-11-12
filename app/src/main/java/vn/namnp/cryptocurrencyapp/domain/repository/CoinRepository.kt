package vn.namnp.cryptocurrencyapp.domain.repository
import kotlinx.coroutines.flow.Flow
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.domain.model.Coin
import vn.namnp.cryptocurrencyapp.domain.model.CoinDetail

interface CoinRepository {

//    suspend fun getCoin(coinId: String): CoinDetail
    suspend fun getCoin(coinId: String): Resource<CoinDetail>

    suspend fun getCoins(): List<Coin>
}