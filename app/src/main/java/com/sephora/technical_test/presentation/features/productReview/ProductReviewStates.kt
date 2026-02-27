package com.sephora.technical_test.presentation.features.productReview

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

sealed class ProductReviewStates {
    data object Initial : ProductReviewStates()
}

sealed class ProductReviewEvents {
    data object Event1 : ProductReviewEvents()
}