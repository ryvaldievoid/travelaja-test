package com.atech.android.data.remote

import com.atech.android.BuildConfig
import com.atech.android.domain.entities.responses.LatestGamesResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("/api/games?key=${BuildConfig.API_KEY}&platforms=4&page=1")
    fun getLatestGames(
        @Query("page_size") pageSize: Int,
        @Query("ordering") ordering: String?,
        @Query("search") search: String?
    ): Flowable<LatestGamesResponse>

}