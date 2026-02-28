package com.sephora.technical_test.presentation.features.products

import androidx.compose.runtime.mutableStateOf
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

enum class ReviewSortOption {
    BEST_TO_WORST,
    WORST_TO_BEST
}

class ProductsBindingModel {
    val inputSearch = mutableStateOf("")
    val sorting = mutableStateOf<ReviewSortOption>(ReviewSortOption.BEST_TO_WORST)
    val products = mutableStateOf<List<ProductResponseData>>(emptyList())
}