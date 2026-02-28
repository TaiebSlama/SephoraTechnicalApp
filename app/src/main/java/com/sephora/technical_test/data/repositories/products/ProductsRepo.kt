package com.sephora.technical_test.data.repositories.products

import com.sephora.technical_test.data.helper.RepositoryResponse
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData
import com.sephora.technical_test.data.repositories.products.payloads.ProductReviewData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
interface ProductsRepo {

    /**
     * [ProductsRepoImpl.fetchProducts]
     * */
    suspend fun fetchProducts(): RepositoryResponse<List<ProductResponseData>>

    /**
     * [ProductsRepoImpl.fetchProductsReviews]
     * */
    suspend fun fetchProductsReviews(): RepositoryResponse<List<ProductReviewData>>

}