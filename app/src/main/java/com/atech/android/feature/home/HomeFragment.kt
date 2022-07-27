package com.atech.android.feature.home

import com.atech.android.base.BaseFragment
import com.atech.android.base.util.showToast
import com.atech.android.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()
    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
        viewModel.test()
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.showToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { message ->
                showToast(message)
            }
        }
    }

}