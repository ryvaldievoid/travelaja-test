package com.atech.android

import com.atech.android.base.BaseApplication
import com.atech.android.di.apiModule
import com.atech.android.di.useCaseModule
import com.atech.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : BaseApplication() {

    override fun getBaseUrl(): String = BuildConfig.API_BASE_URL

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(apiModule, useCaseModule, viewModelModule))
        }
    }

}