package vn.namnp.cryptocurrencyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.namnp.cryptocurrencyapp.common.Constants
import vn.namnp.cryptocurrencyapp.data.remote.CoinApi
import vn.namnp.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import vn.namnp.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(coinApi: CoinApi): CoinRepository {
        return CoinRepositoryImpl(coinApi)
    }
}