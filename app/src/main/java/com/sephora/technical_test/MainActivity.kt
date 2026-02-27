package com.sephora.technical_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sephora.technical_test.presentation.features.products.ProductsScreen
import com.sephora.technical_test.presentation.ui.theme.SEPHORA_Technical_TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SEPHORA_Technical_TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    ProductsScreen()
                }
            }
        }
    }
}