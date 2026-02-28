package com.sephora.technical_test.presentation.features.products

import androidx.lifecycle.viewModelScope
import com.sephora.technical_test.application.base.viewmodel.BaseChannelViewModel
import com.sephora.technical_test.application.isLoading
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
            productManager.fetchProductsList()
            productManager.products.collect {
                stateChannel.send(ProductsStates.ProductsLoaded(it))
            }
        }
    }

    override fun handleEvents(viewEvent: ProductsEvents) {
        when (viewEvent) {
            is ProductsEvents.FetchProductReview -> {
                viewModelScope.launch(Dispatchers.IO) {
                    isLoading.value = true
                    val reviewsResponse = productManager.fetchProductReviewsByID(viewEvent.id)
                    isLoading.value = false
                    viewEvent.reviews(reviewsResponse)
                }

            }
        }
    }

}