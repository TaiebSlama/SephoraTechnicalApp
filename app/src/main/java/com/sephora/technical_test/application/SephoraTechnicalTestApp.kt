package com.sephora.technical_test.application

import android.app.Application
import com.sephora.technical_test.application.di.appManagers
import com.sephora.technical_test.application.di.appModules
import com.sephora.technical_test.application.di.appViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin

/**
 * Created by taieb.slama on 27/02/2026 .
 * Copyright (c) 2023. All rights reserved.
 */
class SephoraTechnicalTestApp : Application() {


    internal lateinit var koinApplication: KoinApplication

    override fun onCreate() {
        super.onCreate()
        // initKoin()
    }

    private fun initKoin() {
        koinApplication = startKoin {
            androidLogger()
            androidContext(this@SephoraTechnicalTestApp)
            modules(appModules, appViewModels, appManagers)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}