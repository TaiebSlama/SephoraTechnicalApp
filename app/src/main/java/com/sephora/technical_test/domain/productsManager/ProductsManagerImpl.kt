package com.sephora.technical_test.domain.productsManager

import com.sephora.technical_test.data.helper.ApiResponse
import com.sephora.technical_test.data.helper.RepositoryResponse
import com.sephora.technical_test.data.repositories.products.ProductsRepo
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
class ProductsManagerImpl(
    private val productRepo: ProductsRepo
) : ProductsManager {

    /**
     * [ProductsManager.fetchProductsList]
     * */
    override suspend fun fetchProductsList(): ApiResponse<List<ProductResponseData>> {
        val data = productRepo.fetchProducts()
        return when (data) {
            is RepositoryResponse.HttpClient -> ApiResponse.Error(data.error.msg())
            is RepositoryResponse.HttpServer -> ApiResponse.Error(data.error.msg())
            is RepositoryResponse.Success -> ApiResponse.Success(data.body)
        }
    }
}