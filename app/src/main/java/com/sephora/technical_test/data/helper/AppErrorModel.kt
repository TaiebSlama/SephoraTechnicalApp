package com.sephora.technical_test.data.helper

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

/**
 * Created by taieb.slama on 07/02/2025 .
 * Copyright (c) 2023. All rights reserved.
 */
@SuppressLint("UnsafeOptInUsageError")
@Serializable
sealed class AppErrorModel : ApiErrorModelsHelper {

    @Serializable
    data class ErrorTemplate(
        var errorCode: String? = null,
        var errorDescription: String? = null,
    ) : AppErrorModel() {
        override fun msg(): String {
            return errorCode ?: "UnknownError"
        }

        override fun code(): String {
            return errorCode ?: "-1"
        }
    }

    data object UnknownErrorModel : AppErrorModel() {
        override fun msg() = "UnknownError"

        override fun code() = "-1"

    }

}

interface ApiErrorModelsHelper {

    fun msg(): String
    fun code(): String

}