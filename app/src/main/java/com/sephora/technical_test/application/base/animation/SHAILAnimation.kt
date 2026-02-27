package com.sephora.technical_test.application.base.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

/**
 * Created by slama.taieb on 12/22/2023 .
 * Copyright (c) 2023 . All rights reserved.
 */

object NavAnimation {
    private const val TRANSITION_DURATION = 800

    val enterTransition =
        slideInHorizontally(
            initialOffsetX = { it / 4 },
            animationSpec = tween(TRANSITION_DURATION)
        ) + fadeIn(animationSpec = tween(TRANSITION_DURATION))

    val exitTransition =
        slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(TRANSITION_DURATION)
        ) + fadeOut(animationSpec = tween(TRANSITION_DURATION))

    val popEnterTransition =
        slideInHorizontally(
            initialOffsetX = { -it / 4 },
            animationSpec = tween(TRANSITION_DURATION)
        ) + fadeIn(animationSpec = tween(TRANSITION_DURATION))

    val popExitTransition =
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(TRANSITION_DURATION)
        ) + fadeOut(animationSpec = tween(TRANSITION_DURATION))

    val enterVerticalTransition = slideInVertically(
        initialOffsetY = { it / 4 },
        animationSpec = tween(TRANSITION_DURATION)
    ) + fadeIn(animationSpec = tween(TRANSITION_DURATION))

    val exitVerticalTransition = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(TRANSITION_DURATION)
    ) + fadeOut(animationSpec = tween(TRANSITION_DURATION))

}

object BottomNavAnimation {

    private const val bottomNavAnimDuration = 400

    val enter = slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(
            durationMillis = bottomNavAnimDuration,
            easing = LinearOutSlowInEasing
        )
    )

    val exit = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(
            durationMillis = bottomNavAnimDuration,
            easing = FastOutLinearInEasing
        )
    )
}

@Composable
fun AnimatedScaleOnAppear(
    duration: Int = 1000,
    delayToAppear: Long = 200,
    content: @Composable () -> Unit
) {
    content()
//    val start = remember { mutableStateOf(false) }
//    AnimatedVisibility(
//        visible = start.value,
//        enter = fadeIn(animationSpec = tween(duration)) + slideInVertically(
//            initialOffsetY = { it / 3 }, // It will start from the bottom and slide up
//            animationSpec = tween(duration)
//        ),
//        exit = fadeOut(animationSpec = tween(100))
//    ) {
//        content()
//    }
//    LaunchedEffect(Unit) {
//        delay(delayToAppear)
//        start.value = true
//    }
}

@Composable
fun AnimatedFadeOnAppear(
    duration: Int = 800,
    delayToAppear: Long = 200,
    content: @Composable () -> Unit
) {
    content()
//    val start = remember { mutableStateOf(false) }
//    AnimatedVisibility(
//        visible = start.value,
//        enter = fadeIn(animationSpec = tween(duration)),
//        exit = fadeOut(animationSpec = tween(100))
//    ) {
//        content()
//    }
//    LaunchedEffect(Unit) {
//        delay(delayToAppear)
//        start.value = true
//    }
}

@Composable
fun AnimatedHorizontalOnAppear(
    duration: Int = 1000,
    delayToAppear: Long = 200,
    content: @Composable () -> Unit
) {
    content()
//    val show = remember {
//        mutableStateOf(false)
//    }
//    AnimatedVisibility(
//        visible = show.value,
//        enter = slideInHorizontally { it / 2 } + fadeIn(animationSpec = tween(duration)),
//        exit = fadeOut(animationSpec = tween(100))
//    ) {
//        content()
//    }
//    LaunchedEffect(Unit) {
//        delay(delayToAppear)
//        show.value = true
//    }
}
