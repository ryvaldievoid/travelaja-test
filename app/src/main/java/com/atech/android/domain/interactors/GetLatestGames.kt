package com.atech.android.domain.interactors

import com.atech.android.domain.FlowableUseCase
import com.atech.android.domain.PostExecutionThread
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.repositories.MainRepository
import io.reactivex.Flowable

class GetLatestGames constructor(
    private val repository: MainRepository,
    postExecutionThread: PostExecutionThread
): FlowableUseCase<LatestGamesResponse, GetLatestGames.Params>(postExecutionThread) {

    override fun build(params: Params): Flowable<LatestGamesResponse> {
        return repository.getLatestGames(params.pageSize, params.ordering, params.search)
    }

    class Params(
        val pageSize: Int,
        val ordering: String? = null,
        val search: String? = null
    )

}
