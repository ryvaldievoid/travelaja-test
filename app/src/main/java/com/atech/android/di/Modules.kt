@file:Suppress("USELESS_CAST")

package com.atech.android.di

import com.atech.android.MyApplication
import com.atech.android.base.config.WebApiProvider
import com.atech.android.base.util.SessionHelper
import com.atech.android.data.remote.MainApi
import com.atech.android.data.repositoriesimpl.MainRepositoryImpl
import com.atech.android.domain.AndroidUIThread
import com.atech.android.domain.PostExecutionThread
import com.atech.android.domain.interactors.GetLatestGames
import com.atech.android.domain.repositories.MainRepository
import com.atech.android.feature.home.HomeViewModel
import com.atech.android.feature.search.SearchViewModel
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {
    single { MyApplication() }
    single { GsonBuilder().create() }
    single { SessionHelper(androidContext(), get()) }
    single { WebApiProvider.getRetrofit(get(), get()) }
    single {
        MainRepositoryImpl(
            get<Retrofit>().create(MainApi::class.java)
        ) as MainRepository
    }
    single { AndroidUIThread() as PostExecutionThread }
}

val useCaseModule = module {
    factory { GetLatestGames(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
    viewModel {
        SearchViewModel(
            get()
        )
    }
}
