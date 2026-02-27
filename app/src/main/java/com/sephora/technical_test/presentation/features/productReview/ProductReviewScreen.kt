package com.sephora.technical_test.presentation.features.productReview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

@Composable
fun ProductReviewScreen(viewModel: ProductReviewViewModel = koinViewModel()) {
    val bindingModel = remember { ProductReviewBindingModel() }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            viewModel.initStates()
            viewModel.observeState {
                handleStates(bindingModel, it)
            }
        }
    }
}

private fun handleStates(bindingModel: ProductReviewBindingModel, it: ProductReviewStates) {
    when (it) {
        ProductReviewStates.Initial -> {}
    }
}