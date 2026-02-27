package com.sephora.technical_test.application.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

/**
 * Created by taieb.slama on 27/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
val appModules = module {

    single {
        HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                url("")
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            engine {
                connectTimeout = TimeUnit.SECONDS.toMillis(30).toInt()
                socketTimeout = TimeUnit.SECONDS.toMillis(30).toInt()
            }

            install(HttpTimeout) {
                requestTimeoutMillis = TimeUnit.SECONDS.toMillis(30)
                socketTimeoutMillis = TimeUnit.SECONDS.toMillis(30)
                connectTimeoutMillis = TimeUnit.SECONDS.toMillis(30)
            }

        }
    }

}

