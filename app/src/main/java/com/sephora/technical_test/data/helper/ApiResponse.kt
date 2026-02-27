package com.sephora.technical_test.data.helper

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val cause: String) : ApiResponse<Nothing>()
}