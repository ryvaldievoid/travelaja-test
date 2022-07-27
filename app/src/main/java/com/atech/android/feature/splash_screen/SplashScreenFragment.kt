package com.atech.android.feature.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atech.android.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private val binding: FragmentSplashScreenBinding by lazy {
        FragmentSplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(
                SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment()
            )
        }, 3_000)
    }

}