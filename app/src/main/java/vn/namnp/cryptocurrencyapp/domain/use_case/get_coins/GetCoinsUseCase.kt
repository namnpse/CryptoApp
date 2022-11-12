package vn.namnp.cryptocurrencyapp.domain.use_case.get_coins

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.data.remote.dto.toCoin
import vn.namnp.cryptocurrencyapp.domain.model.Coin
import vn.namnp.cryptocurrencyapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

//class GetCoinsUseCase @Inject constructor(
//    private val coinRepository: CoinRepository
//) {
//    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
//        try {
//            emit(Resource.Loading<List<Coin>>())
//            val coins = coinRepository.getCoins().map { it.toCoin() }
//            emit(Resource.Success<List<Coin>>(coins))
//        }catch (e: HttpException) {
//            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
//        }catch (e: IOException) {
//            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
//        }catch (e: Exception) {
//            Log.e("TAG", "Exception: "+e.message)
//        }
//    }
//}
// -> should NOT map dto to model in domain layer ( should rather do it in data layer (repository)
// -> The response handling should also rather be done in the repository (catching HttpException & IOException)
// as this is specific to Retrofit and the domain layer shouldn't know about that

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepository.getCoins()
            emit(Resource.Success<List<Coin>>(coins))
        }catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception) {
            Log.e("TAG", "Exception: "+e.message)
        }
    }
}