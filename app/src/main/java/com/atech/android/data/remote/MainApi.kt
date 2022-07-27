package com.atech.android.data.remote

import com.atech.android.BuildConfig
import com.atech.android.domain.entities.responses.LatestGamesResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface MainApi {

    @GET("/api/games?key=${BuildConfig.API_KEY}&page_size=10&ordering=-released&platforms=4&page=1&dates=2021-12-01,2021-12-31")
    fun getLatestGames(): Flowable<LatestGamesResponse>

}