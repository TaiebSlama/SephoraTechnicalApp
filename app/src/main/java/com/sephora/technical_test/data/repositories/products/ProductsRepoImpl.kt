package com.sephora.technical_test.data.repositories.products

import com.sephora.technical_test.data.helper.RepositoryResponse
import com.sephora.technical_test.data.helper.safeRequest
import com.sephora.technical_test.data.repositories.products.ProductsRoutes.GET_PRODUCTS_LIST
import com.sephora.technical_test.data.repositories.products.ProductsRoutes.GET_PRODUCTS_REVIEWS
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData
import com.sephora.technical_test.data.repositories.products.payloads.ProductReviewData
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
class ProductsRepoImpl(private val client: HttpClient) : ProductsRepo {

    /**
     * [ProductsRepo.fetchProducts]
     * */
    override suspend fun fetchProducts(): RepositoryResponse<List<ProductResponseData>> {
        return client.safeRequest {
            method = HttpMethod.Get
            url(GET_PRODUCTS_LIST)
        }
    }

    /**
     * [ProductsRepo.fetchProductsReviews]
     * */
    override suspend fun fetchProductsReviews(): RepositoryResponse<List<ProductReviewData>> {
        return client.safeRequest {
            method = HttpMethod.Get
            url(GET_PRODUCTS_REVIEWS)
        }
    }
}