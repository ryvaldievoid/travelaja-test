package com.atech.android.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atech.android.base.util.SingleEvents
import com.atech.android.base.viewmodel.BaseViewModel
import com.atech.android.domain.entities.responses.LatestGamesResponse
import com.atech.android.domain.entities.responses.Result
import com.atech.android.domain.interactors.GetLatestGames
import com.atech.android.domain.subscriber.DefaultSubscriber
import com.atech.android.domain.subscriber.ResultState


class SearchViewModel(
    private val getLatestGames: GetLatestGames
) : BaseViewModel() {

    private val _searchResult = MutableLiveData<ResultState<List<Result>>>()
    val searchResult : LiveData<ResultState<List<Result>>> = _searchResult
    private val tempSearchResult = mutableListOf<Result>()

    private val _needRefreshRecyclerView = MutableLiveData<SingleEvents<Boolean>>()
    val needRefreshRecyclerView : LiveData<SingleEvents<Boolean>> = _needRefreshRecyclerView

    val searchQuery = MutableLiveData<String>()

    fun search(searchQuery: String) {
        getLatestGames.execute(object : DefaultSubscriber<LatestGamesResponse>() {
            override fun onError(error: ResultState<LatestGamesResponse>) {
                val throwable = (error as ResultState.Error).throwable
                _searchResult.value = ResultState.Error(throwable)
            }

            override fun onSuccess(data: ResultState<LatestGamesResponse>) {
                (data as ResultState.Success).data.let { response ->
                    tempSearchResult.clear()
                    tempSearchResult.addAll(response.results ?: listOf())
                    _searchResult.value = ResultState.Success(tempSearchResult)
                    _needRefreshRecyclerView.value = SingleEvents(true)
                }
            }

        },
        GetLatestGames.Params(
            15,
            search = searchQuery
        ))
    }

}