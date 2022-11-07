package vn.namnp.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import vn.namnp.cryptocurrencyapp.common.Resource
import vn.namnp.cryptocurrencyapp.domain.model.Coin
import vn.namnp.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import vn.namnp.cryptocurrencyapp.presentation.coin_list.CoinListState
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _coinListState = mutableStateOf(CoinListState())
    val coinListState: State<CoinListState> = _coinListState

    init {
        getCoins()
    }

    private fun getCoins() {
        // 1
            getCoinsUseCase()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _coinListState.value = CoinListState(coins = result.data ?: emptyList())
                        }
                        is Resource.Error -> {
                            _coinListState.value =
                                CoinListState(error = result.message ?: "Unexpected error")
                        }
                        is Resource.Loading -> {
                            _coinListState.value = CoinListState(isLoading = true)
                        }
                    }
                }
            .launchIn(viewModelScope) // need this line, if not, it doesn't work

        // block code above equal to this block code,
        // need "viewModelScope.launch",
        // but don't need ".launchIn(viewModelScope)"
        // choose 1 or 2

        //2
//        viewModelScope.launch {
//            getCoinsUseCase()
//                .collect { result ->
//                    Log.e("TAG", "collect $result")
//                    when (result) {
//                        is Resource.Success -> {
//                            _coinListState.value = CoinListState(coins = result.data ?: emptyList())
//                        }
//                        is Resource.Error -> {
//                            _coinListState.value =
//                                CoinListState(error = result.message ?: "Unexpected error")
//                        }
//                        is Resource.Loading -> {
//                            _coinListState.value = CoinListState(isLoading = true)
//                        }
//                    }
//                }
//        }
    }
}