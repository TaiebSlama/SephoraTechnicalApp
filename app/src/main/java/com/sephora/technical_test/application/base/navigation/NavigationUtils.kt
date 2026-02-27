package com.sephora.technical_test.application.base.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sephora.technical_test.application.base.animation.NavAnimation.enterTransition
import com.sephora.technical_test.application.base.animation.NavAnimation.enterVerticalTransition
import com.sephora.technical_test.application.base.animation.NavAnimation.exitTransition
import com.sephora.technical_test.application.base.animation.NavAnimation.exitVerticalTransition
import com.sephora.technical_test.application.base.animation.NavAnimation.popEnterTransition
import com.sephora.technical_test.application.base.animation.NavAnimation.popExitTransition

/**
 * Created by taieb.slama on 15/08/2024 .
 * Copyright (c) 2023. All rights reserved.
 */

fun NavGraphBuilder.addVerticalTransactionScreen(
    route: String,
    content: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { enterVerticalTransition },
        exitTransition = { exitVerticalTransition }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            content()
        }
    }
}

fun NavGraphBuilder.addHorizontalTransactionScreen(
    route: String,
    content: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition }
    ) {
        content()
    }
}