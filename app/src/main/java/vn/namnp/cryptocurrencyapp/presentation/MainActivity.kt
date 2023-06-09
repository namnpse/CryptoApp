package vn.namnp.cryptocurrencyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vn.namnp.cryptocurrencyapp.R
import vn.namnp.cryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import vn.namnp.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import vn.namnp.cryptocurrencyapp.presentation.ui.Screen
import vn.namnp.cryptocurrencyapp.presentation.ui.theme.CryptocurrencyAppTheme

// -> should NOT map dto to model in domain layer ( should rather do it in data layer (repository)
// -> The response handling should also rather be done in the repository (catching HttpException & IOException)
// as this is specific to Retrofit and the domain layer shouldn't know about that
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}