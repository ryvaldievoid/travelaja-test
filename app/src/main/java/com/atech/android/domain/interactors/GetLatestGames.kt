package com.atech.android.domain.interactors

import com.atech.android.domain.FlowableUseCase
import com.atech.android.domain.PostExecutionThread
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.repositories.MainRepository
import io.reactivex.Flowable

class GetLatestGames constructor(
    private val repository: MainRepository,
    postExecutionThread: PostExecutionThread
): FlowableUseCase<LatestGamesResponse, Void?>(postExecutionThread) {

    override fun build(params: Void?): Flowable<LatestGamesResponse> {
        return repository.getLatestGames()
    }

}
