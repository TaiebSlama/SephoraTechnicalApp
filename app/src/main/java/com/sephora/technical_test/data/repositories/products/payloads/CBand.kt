package com.sephora.technical_test.data.repositories.products.payloads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
@Serializable
data class CBrand(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)