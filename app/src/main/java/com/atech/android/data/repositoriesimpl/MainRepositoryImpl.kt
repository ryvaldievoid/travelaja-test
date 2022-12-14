package com.atech.android.data.repositoriesimpl

import com.atech.android.data.remote.MainApi
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.repositories.MainRepository
import io.reactivex.Flowable

class MainRepositoryImpl(private val api: MainApi): MainRepository {
    override fun getLatestGames(
        pageSize: Int,
        ordering: String?,
        search: String?
    ): Flowable<LatestGamesResponse> {
        return api.getLatestGames(pageSize, ordering, search)
    }
}