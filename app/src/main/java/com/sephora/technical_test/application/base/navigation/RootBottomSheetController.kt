package com.sephora.technical_test.application.base.navigation

/**
 * Created by slama.taieb on 1/25/2024 .
 * Copyright (c) 2023 . All rights reserved.
 */
sealed class RootBottomSheetController<out T : Any> {
    data class ExpandSheet<out T : Any>(val sheet: T) : RootBottomSheetController<T>()
    data class ShowSheet<out T : Any>(val sheet: T) : RootBottomSheetController<T>()
    data object HideSheet : RootBottomSheetController<Nothing>()
}