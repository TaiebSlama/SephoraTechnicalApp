package com.sephora.technical_test.data.repositories.products.payloads


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductReviewData(
    @SerialName("hide")
    val hide: Boolean? = null,
    @SerialName("product_id")
    val productId: Int? = null,
    @SerialName("reviews")
    val reviews: List<Review> = listOf()
)

@Serializable
data class Review(
    @SerialName("name")
    val name: String? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("text")
    val text: String? = null
)