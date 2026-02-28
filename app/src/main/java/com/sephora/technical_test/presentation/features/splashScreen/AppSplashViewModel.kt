package com.sephora.technical_test.presentation.features.splashScreen

import androidx.lifecycle.viewModelScope
import com.sephora.technical_test.application.base.viewmodel.BaseChannelViewModel
import com.sephora.technical_test.domain.productsManager.ProductsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

class AppSplashViewModel(
    private val productManager: ProductsManager
) : BaseChannelViewModel<AppSplashStates, AppSplashEvents>() {

    override var stateChannel: Channel<AppSplashStates> = Channel()

    override fun initStates() {
        viewModelScope.launch(Dispatchers.IO) {
            productManager.fetchProductsList()
            stateChannel.send(AppSplashStates.DataLoaded)
        }
    }

    override fun handleEvents(viewEvent: AppSplashEvents) {
        when (viewEvent) {
            AppSplashEvents.Event1 -> {}
        }
    }

}