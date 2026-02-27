package com.sephora.technical_test.presentation.features.products

import androidx.compose.runtime.mutableStateOf
import com.sephora.technical_test.data.helper.AsyncDataLoader
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

class ProductsBindingModel {
    val products =
        mutableStateOf<AsyncDataLoader<List<ProductResponseData>>>(AsyncDataLoader.Loading)
}