package com.atech.android.feature.home

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.airbnb.epoxy.Carousel
import com.atech.android.TopGamesItemBindingModel_
import com.atech.android.base.BaseFragment
import com.atech.android.base.util.hide
import com.atech.android.base.util.show
import com.atech.android.base.util.showToast
import com.atech.android.databinding.FragmentHomeBinding
import com.atech.android.domain.subscriber.ResultState
import com.atech.android.topGamesItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
        initTopRatedRecyclerView()
        initLatestRecyclerView()
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.showToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                showToast(message)
            }
        }

        viewModel.needRefreshCarousel.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { _ ->
                binding.topRatedRecycler.requestModelBuild()
            }
        }

        viewModel.needRefreshRecyclerView.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { _ ->
                binding.latestRecycler.requestModelBuild()
            }
        }
    }

    private fun initTopRatedRecyclerView() {
        Carousel.setDefaultGlobalSnapHelperFactory(null)
        binding.topRatedRecycler.apply {
            withModels {
                viewModel.topRatedGames.value?.let { resultState ->
                    when(resultState) {
                        is ResultState.Loading -> {
                            hide()
                            binding.progressBar.show()
                        }
                        is ResultState.Success -> {
                            show()
                            binding.progressBar.hide()
                            resultState.data.let { items ->
                                val bannerModel = items.mapIndexed { index, model ->
                                    TopGamesItemBindingModel_()
                                        .id("top rated games $index")
                                        .viewModel(viewModel)
                                        .model(model)
                                }
                                setModels(bannerModel)
                                setPadding(
                                    Carousel.Padding.dp(
                                        paddingLeft,
                                        paddingTop,
                                        paddingRight,
                                        paddingBottom,
                                        0
                                    )
                                )
                                val snapHelper = PagerSnapHelper()
                                onFlingListener = null
                                snapHelper.attachToRecyclerView(this@apply)
                                layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            }
                        }
                        else -> {
                            show()
                            binding.progressBar.hide()
                        }
                    }
                }
            }
        }
    }

    private fun initLatestRecyclerView() {
        binding.latestRecycler.withModels {
            viewModel.latestGames.value?.let { resultState ->
                when (resultState) {
                    is ResultState.Loading -> {
                        binding.latestRecycler.hide()
                        binding.progressBar2.show()
                    }
                    is ResultState.Success -> {
                        binding.latestRecycler.show()
                        binding.progressBar2.hide()
                        resultState.data.let { items ->
                            items.mapIndexed { index, model ->
                                topGamesItem {
                                    id("latest games $index")
                                    viewModel(viewModel)
                                    model(model)
                                }
                            }
                        }
                    }
                    else -> {
                        binding.latestRecycler.show()
                        binding.progressBar2.hide()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.apply {
            getTopRatedGames()
            getLatestGames()
        }
    }

}