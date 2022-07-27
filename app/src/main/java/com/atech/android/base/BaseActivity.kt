package com.atech.android.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.atech.android.navigation.NavigationFlow
import com.atech.android.navigation.ToFlowNavigatable


/**
 * Created by Abraham Lay on 2020-06-09.
 */

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity(),
    ToFlowNavigatable {

    abstract val binding: VB
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onInitViews()
        onInitObservers()
    }

    abstract fun onInitViews()

    abstract fun onInitObservers()


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateToFlow(flow: NavigationFlow) {
//        navigator.navigateToFlow(flow)
    }
}