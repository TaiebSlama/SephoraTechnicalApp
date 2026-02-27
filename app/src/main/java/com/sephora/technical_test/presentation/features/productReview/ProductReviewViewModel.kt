package com.sephora.technical_test.presentation.features.productReview

import com.sephora.technical_test.application.base.viewmodel.BaseChannelViewModel
import kotlinx.coroutines.channels.Channel

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

class ProductReviewViewModel : BaseChannelViewModel<ProductReviewStates, ProductReviewEvents>() {

    override var stateChannel: Channel<ProductReviewStates> = Channel()

    override fun initStates() {}

    override fun handleEvents(viewEvent: ProductReviewEvents) {
        when (viewEvent) {
            ProductReviewEvents.Event1 -> {}
        }
    }

}