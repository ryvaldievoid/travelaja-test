package com.atech.android.feature.search

import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.atech.android.base.BaseFragment
import com.atech.android.base.util.hide
import com.atech.android.base.util.hideKeyboard
import com.atech.android.base.util.show
import com.atech.android.base.util.showToast
import com.atech.android.databinding.FragmentSearchBinding
import com.atech.android.domain.subscriber.ResultState
import com.atech.android.topGamesItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel()
    override val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
        binding.apply {
            lifecycleOwner = this@SearchFragment.viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
        }
        initSearchBar()
        initRecyclerView()
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.showToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                showToast(message)
            }
        }

        viewModel.needRefreshRecyclerView.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { _ ->
                binding.searchRecycler.requestModelBuild()
            }
        }

        viewModel.searchQuery.observe(viewLifecycleOwner) {
            if (it.length > 2) {
                viewModel.search(it)
            }
        }
    }

    private fun initSearchBar() {
        binding.searchLayout.searchBar.apply {
            requestFocus()
            inputType = InputType.TYPE_CLASS_TEXT
            setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    view.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun initRecyclerView() {
        binding.searchRecycler.withModels {
            viewModel.searchResult.value?.let { resultState ->
                when (resultState) {
                    is ResultState.Loading -> {
                        binding.apply {
                            progressBar.show()
                            searchRecycler.hide()
                        }
                    }
                    is ResultState.Success -> {
                        binding.apply {
                            progressBar.hide()
                            searchRecycler.show()
                        }
                        resultState.data.let { items ->
                            items.mapIndexed { index, model ->
                                topGamesItem {
                                    id("latest games $index")
                                    model(model)
                                }
                            }
                        }
                    }
                    is ResultState.Error -> {
                        showToast(resultState.throwable.message ?: "")
                        binding.apply {
                            progressBar.hide()
                            searchRecycler.show()
                        }
                    }
                }
            }
        }
    }

}