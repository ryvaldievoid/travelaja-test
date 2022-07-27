package com.atech.android.feature.home

import com.atech.android.base.util.SingleEvents
import com.atech.android.base.viewmodel.BaseViewModel
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.interactors.GetLatestGames
import com.atech.android.domain.subscriber.DefaultSubscriber
import com.atech.android.domain.subscriber.ResultState


class HomeViewModel(
    private val getLatestGames: GetLatestGames
) : BaseViewModel() {

    fun test() {
        getLatestGames.execute(
            object: DefaultSubscriber<LatestGamesResponse>() {
                override fun onError(error: ResultState<LatestGamesResponse>) {

                }

                override fun onSuccess(data: ResultState<LatestGamesResponse>) {
                    _showToast.value = SingleEvents("hore")
                }

            },
            null
        )
    }

}