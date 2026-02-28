package com.sephora.technical_test.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sephora.technical_test.application.base.navigation.NavigatorFlow
import com.sephora.technical_test.application.base.navigation.addHorizontalTransactionScreen
import com.sephora.technical_test.presentation.features.productReview.ProductReviewScreen
import com.sephora.technical_test.presentation.features.products.ProductsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            // Scaffold to handle top/bottom bars if needed
            Scaffold { paddingValues ->
                // Respect safe area / padding from Scaffold
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(WindowInsets.safeDrawing.asPaddingValues())
                ) {
                    // Main content
                    MainScreen()
                    // Full-screen loader overlay
                    FullScreenLoader()
                }
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
        startDestination = MainRoutes.Products.route
    ) {

        addHorizontalTransactionScreen(route = MainRoutes.Products.route) {
            ProductsScreen()
        }
        addHorizontalTransactionScreen(route = MainRoutes.ProductReview.route) {
            ProductReviewScreen()
        }

    }
}


val isLoading = mutableStateOf(false)

@Composable
private fun FullScreenLoader() {
    if (isLoading.value)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .alpha(0.5f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
}