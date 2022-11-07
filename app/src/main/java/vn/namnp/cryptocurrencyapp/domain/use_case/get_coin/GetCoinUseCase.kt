package vn.namnp.cryptocurrencyapp.domain.use_case.get_coin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.data.remote.dto.toCoin
import vn.namnp.cryptocurrencyapp.data.remote.dto.toCoinDetail
import vn.namnp.cryptocurrencyapp.domain.model.Coin
import vn.namnp.cryptocurrencyapp.domain.model.CoinDetail
import vn.namnp.cryptocurrencyapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = coinRepository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}