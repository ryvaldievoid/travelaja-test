package com.atech.android.domain.repositories

import com.atech.android.domain.entities.responses.LatestGamesResponse
import io.reactivex.Flowable

interface MainRepository {

    fun getLatestGames(): Flowable<LatestGamesResponse>

}