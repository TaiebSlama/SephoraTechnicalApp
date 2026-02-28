package com.sephora.technical_test.presentation.features.products

import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData
import com.sephora.technical_test.data.repositories.products.payloads.ProductReviewData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

sealed class ProductsStates {
    data object LoadingProducts : ProductsStates()
    data class ProductsLoaded(val data: List<ProductResponseData>) : ProductsStates()
}

sealed class ProductsEvents {
    data class FetchProductReview(val id: Int, val reviews: (ProductReviewData?) -> Unit) :
        ProductsEvents()
}