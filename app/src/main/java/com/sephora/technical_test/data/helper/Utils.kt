package com.sephora.technical_test.data.helper

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import kotlinx.serialization.json.Json

/**
 * Created by slama.taieb on 12/22/2023 .
 * Copyright (c) 2023 . All rights reserved.
 */
suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): RepositoryResponse<T> =
    try {
        val response: HttpResponse = request { block() }
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "url = " + response.request.url.toString())
        Log.d("HttpRequestBuilder", "content = " + response.request.content.toString())
        Log.d("HttpRequestBuilder", "method = " + response.request.method.toString())
        Log.d("HttpRequestBuilder", "headers = " + response.request.headers.toString())
        Log.d("HttpRequestBuilder", "attributes = " + response.request.attributes.toString())
        Log.d("HttpRequestBuilder", "code result = " + response.status.value.toString())
        Log.d("HttpRequestBuilder", "response body = " + response.body<String>().toString())
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")
        Log.d("HttpRequestBuilder", "////////////////////////////////////////////////")

        when (response.status.value) {
            in 200..299 -> RepositoryResponse.Success(response.body())
            in 400..499 -> {
                val error = response.body<String>()
                val model = parseErrorResponse(error)
                RepositoryResponse.HttpClient(response.status.value, model)
            }

            else -> {
                val error = response.body<String>()
                val model = parseErrorResponse(error)
                RepositoryResponse.HttpServer(500, model)
            }
        }
    } catch (e: ClientRequestException) {
        RepositoryResponse.HttpClient(
            e.response.status.value,
            AppErrorModel.UnknownErrorModel
        )
    } catch (e: ServerResponseException) {
        RepositoryResponse.HttpServer(
            e.response.status.value,
            AppErrorModel.UnknownErrorModel
        )
    } catch (e: Exception) {
        Log.e(
            "HttpRequestBuilder",
            e.message?.ifEmpty { "Exception message empty" } ?: "Exception message null")
        RepositoryResponse.HttpServer(-1, AppErrorModel.UnknownErrorModel)
    }

sealed class RepositoryResponse<out T> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<T>(val body: T) : RepositoryResponse<T>()

    /**
     * Represents client (40x) errors.
     */
    data class HttpClient(val code: Int, val error: AppErrorModel) : RepositoryResponse<Nothing>()

    /**
     * Represents server (50x) errors
     */
    data class HttpServer(val code: Int, val error: AppErrorModel) : RepositoryResponse<Nothing>()
}

fun parseErrorResponse(response: String): AppErrorModel {
    return try {
        val json = Json { ignoreUnknownKeys = true }
        json.decodeFromString<AppErrorModel.ErrorTemplate>(response)
    } catch (e: Exception) {
        AppErrorModel.UnknownErrorModel
    }
}
