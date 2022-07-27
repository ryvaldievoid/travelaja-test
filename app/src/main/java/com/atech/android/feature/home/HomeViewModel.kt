package com.atech.android.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atech.android.base.util.SingleEvents
import com.atech.android.base.viewmodel.BaseViewModel
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.entities.responses.Result
import com.atech.android.domain.interactors.GetLatestGames
import com.atech.android.domain.subscriber.DefaultSubscriber
import com.atech.android.domain.subscriber.ResultState


class HomeViewModel(
    private val getLatestGames: GetLatestGames
) : BaseViewModel() {

    private val _topRatedGames = MutableLiveData<ResultState<List<Result>>>()
    val topRatedGames : LiveData<ResultState<List<Result>>> = _topRatedGames
    private val tempRatedList = mutableListOf<Result>()

    private val _latestGames = MutableLiveData<ResultState<List<Result>>>()
    val latestGames : LiveData<ResultState<List<Result>>> = _latestGames

    private val _needRefreshRecyclerView = MutableLiveData<SingleEvents<Boolean>>()
    val needRefreshRecyclerView : LiveData<SingleEvents<Boolean>> = _needRefreshRecyclerView

    private val _needRefreshCarousel = MutableLiveData<SingleEvents<Boolean>>()
    val needRefreshCarousel : LiveData<SingleEvents<Boolean>> = _needRefreshCarousel

    fun getTopRatedGames() {
        getLatestGames.execute(
            object: DefaultSubscriber<LatestGamesResponse>() {
                override fun onError(error: ResultState<LatestGamesResponse>) {
                    val throwable = (error as ResultState.Error).throwable
                    _topRatedGames.value = ResultState.Error(throwable)
                }

                override fun onSuccess(data: ResultState<LatestGamesResponse>) {
                    (data as ResultState.Success).data.let { response ->
                        if (tempRatedList.isEmpty()) {
                            tempRatedList.addAll(response.results ?: listOf())
                            _topRatedGames.value = ResultState.Success(tempRatedList)
                            _needRefreshCarousel.value = SingleEvents(true)
                        }
                    }
                }

            },
            GetLatestGames.Params(
                10,
                TOP_ORDER
            )
        )
    }

    fun getLatestGames() {
        getLatestGames.execute(
            object: DefaultSubscriber<LatestGamesResponse>() {
                override fun onError(error: ResultState<LatestGamesResponse>) {
                    val throwable = (error as ResultState.Error).throwable
                    _latestGames.value = ResultState.Error(throwable)
                    _needRefreshRecyclerView.value = SingleEvents(false)
                }

                override fun onSuccess(data: ResultState<LatestGamesResponse>) {
                    (data as ResultState.Success).data.let { response ->
                        _latestGames.value = ResultState.Success(
                            response.results ?: listOf()
                        )
                        _needRefreshRecyclerView.value = SingleEvents(true)
                    }
                }

            },
            GetLatestGames.Params(
                15,
                LATEST_ORDER
            )
        )
    }

    companion object {
        const val LATEST_ORDER = "-released"
        const val TOP_ORDER = "-rating"
    }

}