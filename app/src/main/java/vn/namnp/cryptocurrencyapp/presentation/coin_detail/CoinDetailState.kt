package vn.namnp.cryptocurrencyapp.presentation.coin_detail

import vn.namnp.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
