package com.sephora.technical_test.presentation.features.products

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.sephora.technical_test.data.helper.AsyncDataLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = koinViewModel()) {
    val bindingModel = remember { ProductsBindingModel() }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            viewModel.initStates()
            viewModel.observeState {
                handleStates(bindingModel, it)
            }
        }
    }
}

private fun handleStates(bindingModel: ProductsBindingModel, it: ProductsStates) {
    Log.d("ProductsScreen", "handleStates: $it")
    when (it) {
        ProductsStates.LoadingProducts -> bindingModel.products.value = AsyncDataLoader.Loading
        is ProductsStates.FailToLoadProducts -> bindingModel.products.value =
            AsyncDataLoader.LoaderFailure

        is ProductsStates.ProductsLoaded -> bindingModel.products.value =
            AsyncDataLoader.Finish(it.data)
    }
}