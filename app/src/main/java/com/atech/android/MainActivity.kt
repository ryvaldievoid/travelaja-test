package com.atech.android

import androidx.activity.viewModels
import com.atech.android.base.BaseActivity
import com.atech.android.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun onInitViews() = Unit

    override fun onInitObservers() = Unit

}