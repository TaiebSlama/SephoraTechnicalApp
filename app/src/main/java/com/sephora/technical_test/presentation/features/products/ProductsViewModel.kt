package com.sephora.technical_test.presentation.features.products

import androidx.lifecycle.viewModelScope
import com.rta.suhail.application.common.base.viewmodel.BaseChannelViewModel
import com.sephora.technical_test.data.helper.ApiResponse
import com.sephora.technical_test.domain.productsManager.ProductsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

class ProductsViewModel(
    private val productManager: ProductsManager
) : BaseChannelViewModel<ProductsStates, ProductsEvents>() {

    override var stateChannel: Channel<ProductsStates> = Channel()

    override fun initStates() {
        viewModelScope.launch(Dispatchers.IO) {
            stateChannel.send(ProductsStates.LoadingProducts)
            when (val response = productManager.fetchProductsList()) {
                is ApiResponse.Error -> stateChannel.send(ProductsStates.FailToLoadProducts(response.cause))
                is ApiResponse.Success -> stateChannel.send(ProductsStates.ProductsLoaded(response.data))
            }
        }
    }

    override fun handleEvents(viewEvent: ProductsEvents) {
        when (viewEvent) {
            ProductsEvents.Event1 -> {}
        }
    }

}