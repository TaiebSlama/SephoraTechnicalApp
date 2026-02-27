package com.sephora.technical_test.domain.productsManager

import com.sephora.technical_test.data.helper.ApiResponse
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
interface ProductsManager {

    /**
     * [ProductsManagerImpl.fetchProductsList]
     * */
    suspend fun fetchProductsList(): ApiResponse<List<ProductResponseData>>

}