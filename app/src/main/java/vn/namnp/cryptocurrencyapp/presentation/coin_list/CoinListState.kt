package vn.namnp.cryptocurrencyapp.presentation.coin_list
import vn.namnp.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
