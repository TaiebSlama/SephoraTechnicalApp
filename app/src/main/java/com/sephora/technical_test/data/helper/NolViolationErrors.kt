package com.sephora.technical_test.data.helper

import kotlinx.serialization.Serializable

/**
 * Created by taieb.slama on 17/09/2025 .
 * Copyright (c) 2023. All rights reserved.
 */
@Serializable
data class NolViolationErrors(
    var errorCode: Int? = -1,
    var errorMessage: String? = null,
)
