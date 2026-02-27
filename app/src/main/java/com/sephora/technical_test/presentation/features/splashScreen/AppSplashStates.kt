package com.sephora.technical_test.presentation.features.splashScreen

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

sealed class AppSplashStates {
    data object Initial : AppSplashStates()
}

sealed class AppSplashEvents {
    data object Event1 : AppSplashEvents()
}