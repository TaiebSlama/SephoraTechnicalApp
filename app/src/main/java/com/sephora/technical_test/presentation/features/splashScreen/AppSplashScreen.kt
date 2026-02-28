package com.sephora.technical_test.presentation.features.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sephora.technical_test.application.MainRoutes
import com.sephora.technical_test.application.base.navigation.RootNavigator
import com.sephora.technical_test.application.mainRootNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

@Composable
fun AppSplashScreen() {
    val viewModel: AppSplashViewModel = koinViewModel()
    val bindingModel = remember { AppSplashBindingModel() }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            viewModel.initStates()
            viewModel.observeState {
                handleStates(bindingModel, it)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "SOPHORA",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

private fun handleStates(bindingModel: AppSplashBindingModel, it: AppSplashStates) {
    when (it) {
        AppSplashStates.DataLoaded -> mainRootNavigator.apply(
            RootNavigator.NavigateToAndClearBackTask(
                MainRoutes.Products
            )
        )
    }
}