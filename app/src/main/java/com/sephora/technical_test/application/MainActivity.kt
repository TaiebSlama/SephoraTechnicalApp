package com.sephora.technical_test.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sephora.technical_test.application.base.navigation.NavigatorFlow
import com.sephora.technical_test.application.base.navigation.addHorizontalTransactionScreen
import com.sephora.technical_test.presentation.features.productReview.ProductReviewScreen
import com.sephora.technical_test.presentation.features.products.ProductsScreen
import com.sephora.technical_test.presentation.features.splashScreen.AppSplashScreen
import com.sephora.technical_test.presentation.ui.theme.SEPHORA_Technical_TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SEPHORA_Technical_TestTheme {
                MainScreen()
            }
        }
    }
}

val mainRootNavigator: NavigatorFlow<MainRoutes> = NavigatorFlow()

@Composable
private fun MainScreen() {
    val mainNavController = rememberNavController()
    LaunchedEffect(key1 = true) {
        mainRootNavigator.initialize(mainNavController)
    }
    NavHost(
        modifier = Modifier,
        navController = mainNavController,
        startDestination = MainRoutes.Splash.route
    ) {
        addHorizontalTransactionScreen(route = MainRoutes.Splash.route) {
            AppSplashScreen()
        }
        addHorizontalTransactionScreen(route = MainRoutes.Products.route) {
            ProductsScreen()
        }
        addHorizontalTransactionScreen(route = MainRoutes.ProductReview.route) {
            ProductReviewScreen()
        }

    }
}