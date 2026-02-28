package com.sephora.technical_test.data.repositories.products.payloads


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseData(
    @SerialName("c_brand")
    val cBrand: CBrand?,
    @SerialName("description")
    val description: String?,
    @SerialName("images_url")
    val imagesUrl: ImagesUrl?,
    @SerialName("is_productSet")
    val isProductSet: Boolean?,
    @SerialName("is_special_brand")
    val isSpecialBrand: Boolean = false,
    @SerialName("price")
    val price: Double?,
    @SerialName("product_id")
    val productId: Int?,
    @SerialName("product_name")
    val productName: String?
)