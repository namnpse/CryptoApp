package vn.namnp.cryptocurrencyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinApplication: Application()

// Improvement:
// 1. should NOT map dto to model in domain layer ( should rather do it in data layer (repository)
// 2. The response handling should also rather be done in the repository (catching HttpException & IOException)
// as this is specific to Retrofit and the domain layer shouldn't know about that