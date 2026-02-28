package com.sephora.technical_test.presentation.features.products

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sephora.technical_test.R
import com.sephora.technical_test.data.helper.AsyncDataLoader
import com.sephora.technical_test.data.repositories.products.payloads.ProductResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

/**
 * Created by taieb.slama on 28/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = koinViewModel()) {
    val bindingModel = remember { ProductsBindingModel() }
    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.IO) {
            viewModel.initStates()
            viewModel.observeState {
                handleStates(bindingModel, it)
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SearchInputField(bindingModel)
        ProductsState(bindingModel)
    }
}

@Composable
private fun ColumnScope.ProductsState(bindingModel: ProductsBindingModel) {
    // case loading products
    AnimatedVisibility(
        visible = bindingModel.products.value is AsyncDataLoader.Loading
    ) {
        FullScreenLoader()
    }
    // case products loaded fail or empty
    AnimatedVisibility(
        visible = bindingModel.products.value is AsyncDataLoader.Finish &&
                (bindingModel.products.value as AsyncDataLoader.Finish<List<ProductResponseData>>).data.isEmpty()
    ) {
        NoProductsFound()
    }
    // case dara retrieved
    AnimatedVisibility(
        visible = bindingModel.products.value is AsyncDataLoader.Finish &&
                (bindingModel.products.value as AsyncDataLoader.Finish<List<ProductResponseData>>).data.isNotEmpty()
    ) {
        ProductList(bindingModel)
    }
}

@Composable
private fun ProductList(bindingModel: ProductsBindingModel) {
    val list =
        (bindingModel.products.value as AsyncDataLoader.Finish<List<ProductResponseData>>).data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        list.map {
            ProductItem(it) {}
        }
    }
}

@Composable
fun ProductItem(product: ProductResponseData, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onSelect() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image
            AsyncImage(
                model = product.imagesUrl?.small,
                contentDescription = product.productName,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Brand
                Text(
                    text = product.cBrand.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Product Name
                Text(
                    text = product.productName.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))
                // Description
                Text(
                    text = product.description.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Price
                    Text(
                        text = "${product.price} AED",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    if (product.isSpecialBrand) {
                        Text(
                            text = "SPECIAL",
                            color = Color.White,
                            modifier = Modifier
                                .background(
                                    Color.Red,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NoProductsFound() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.sophera_is_getting_ready_products_will_be_available_soon),
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun FullScreenLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SearchInputField(binding: ProductsBindingModel) {

}

private fun handleStates(bindingModel: ProductsBindingModel, it: ProductsStates) {
    when (it) {
        ProductsStates.LoadingProducts -> bindingModel.products.value = AsyncDataLoader.Loading
        is ProductsStates.FailToLoadProducts -> bindingModel.products.value =
            AsyncDataLoader.LoaderFailure

        is ProductsStates.ProductsLoaded -> bindingModel.products.value =
            AsyncDataLoader.Finish(it.data)
    }
}