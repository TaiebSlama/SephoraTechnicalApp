package com.sephora.technical_test.application.base.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by slama.taieb on 12/21/2023 .
 * Copyright (c) 2023 . All rights reserved.
 */

open class BaseRoute(var route: String)

sealed class RootNavigator<out T : BaseRoute>(var case: String = "") {
    data class NavigateTo<out T : BaseRoute>(val destination: T, val scenarios: String = "") :
        RootNavigator<T>(case = scenarios)

    data class NavigateToAndClearBackTask<out T : BaseRoute>(val destination: T) :
        RootNavigator<T>()

    data class NavigateToAndClearUntil<out T : BaseRoute>(val destination: T, val until: T) :
        RootNavigator<T>()

    data class NavigateToAndDoNotSaveInBackTask<out T : BaseRoute>(val destination: T) :
        RootNavigator<T>()

    data object NavigateBack : RootNavigator<Nothing>()
    data class PopStackTo<out T : BaseRoute>(val destination: T) : RootNavigator<T>()

}

class NavigatorFlow<T : BaseRoute> {
    private val scopeMain = CoroutineScope(Dispatchers.Main)
    private val scopeIO = CoroutineScope(Dispatchers.Default)

    var current = mutableStateOf("")
    private var navController: NavController? = null
    private var collectCallback: (suspend (value: RootNavigator<T>) -> Unit)? = null

    fun initialize(
        navController: NavController,
        collectCallback: (suspend (value: RootNavigator<T>) -> Unit)? = null
    ) {
        this.navController = navController
        this.collectCallback = collectCallback
        scopeIO.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                current.value = it.destination.route.toString()
            }
        }
    }

    fun apply(root: RootNavigator<T>, backStackEmpty: (() -> Unit)? = null) {
        scopeMain.launch {
            if (navController?.previousBackStackEntry == null &&
                root == RootNavigator.NavigateBack
            ) {
                backStackEmpty?.invoke()
            } else {
                if (collectCallback != null) {
                    collectCallback?.let { it(root) }
                } else
                    defaultNavigatorBehaviour(root)
            }
        }
    }

    private fun defaultNavigatorBehaviour(root: RootNavigator<T>) {
        navController?.let { nav ->
            when (root) {
                RootNavigator.NavigateBack ->
                    if (nav.previousBackStackEntry != null) nav.navigateUp() else {
                    }

                is RootNavigator.NavigateTo -> nav.navigate(root.destination.route)

                is RootNavigator.NavigateToAndClearUntil -> {
                    nav.navigate(root.destination.route) {
                        popUpTo(root.until.route) { inclusive = false }
                    }
                }

                is RootNavigator.PopStackTo -> nav.popBackStack(root.destination.route, false)

                is RootNavigator.NavigateToAndClearBackTask -> {
                    nav.navigate(root.destination.route) {
                        popUpTo(nav.graph.id) {
                            inclusive = true
                            saveState = true // This will save the state of the current destination
                        }
                        restoreState = true // This will restore the state when navigating back
                    }
                }

                is RootNavigator.NavigateToAndDoNotSaveInBackTask -> {
                    nav.navigate(root.destination.route) {
                        popUpTo(nav.currentDestination?.id ?: return@navigate) {
                            inclusive = true
                            saveState = true // This will save the state of the current destination
                        }
                        restoreState = true // This will restore the state when navigating back
                    }
                }
            }
        }
    }
}