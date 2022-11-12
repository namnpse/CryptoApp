package vn.namnp.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.namnp.cryptocurrencyapp.common.Constants
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import vn.namnp.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import javax.inject.Inject
@HiltViewModel
class DetailCoinViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
//    private val getCoinsUseCase: GetCoinsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinState = mutableStateOf(CoinDetailState())
    val coinState: State<CoinDetailState> = _coinState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getDetailCoin(coinId)
        }
    }

    private fun getDetailCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinState.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _coinState.value =
                        CoinDetailState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _coinState.value = CoinDetailState(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope) // need this line, if not, it doesn't work
    }
}