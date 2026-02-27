package com.sephora.technical_test.data.helper

/**
 * Created by slama taieb on 3/8/2024 .
 * Copyright (c) 2023. All rights reserved.
 */
sealed class AsyncDataLoader<out T : Any> {
    data class Finish<out T : Any>(val data: T) : AsyncDataLoader<T>()
    data object LoaderFailure : AsyncDataLoader<Nothing>()
    data object Loading : AsyncDataLoader<Nothing>()
}