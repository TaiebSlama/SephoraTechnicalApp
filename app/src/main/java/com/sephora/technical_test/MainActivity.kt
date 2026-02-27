package com.sephora.technical_test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sephora.technical_test.data.helper.RepositoryResponse
import com.sephora.technical_test.data.helper.resolveError
import com.sephora.technical_test.data.repositories.products.ProductsRepo
import com.sephora.technical_test.presentation.ui.theme.SEPHORA_Technical_TestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
    val productRepo: ProductsRepo by inject(ProductsRepo::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val response = productRepo.fetchProducts()
                    when (response) {
                        is RepositoryResponse.Success -> {
                            Log.d("HttpRequestBuilder", response.body.toString())
                        }

                        else -> Log.d("HttpRequestBuilder", response.resolveError())
                    }
                }
            }
            SEPHORA_Technical_TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SEPHORA_Technical_TestTheme {
        Greeting("Android")
    }
}