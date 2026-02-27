package com.sephora.technical_test.data.repositories.products.payloads


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesUrl(
    @SerialName("large")
    val large: String?,
    @SerialName("small")
    val small: String?
)