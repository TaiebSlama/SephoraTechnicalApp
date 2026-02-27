package com.sephora.technical_test.data.repositories.products.payloads


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CBrand(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)