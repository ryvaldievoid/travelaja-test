package com.atech.android.base.config

import com.atech.android.MyApplication
import com.atech.android.base.util.SessionHelper
import com.atech.base.config.OAuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object WebApiProvider {
    fun getRetrofit(myApplication: MyApplication, sessionHelper: SessionHelper): Retrofit = Retrofit
        .Builder()
        .baseUrl(myApplication.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(
                    OAuthInterceptor(
                        OAuthInterceptor.BEARER,
                        sessionHelper
                    )
                )
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()
        )
        .build()
}