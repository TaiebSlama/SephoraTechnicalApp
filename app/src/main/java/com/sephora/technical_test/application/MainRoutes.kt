package com.sephora.technical_test.application

import com.sephora.technical_test.application.base.navigation.BaseRoute

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

sealed class MainRoutes(route: String) : BaseRoute(route = route) {

    data object Splash : MainRoutes(route = "Splash")
    data object Products : MainRoutes(route = "Products")
    data object ProductReview : MainRoutes(route = "ProductReview")

}