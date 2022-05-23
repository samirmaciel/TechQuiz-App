package com.samirmaciel.techquiz.view.main.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding? = null
    private val binding : FragmentSplashBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.bind(inflater.inflate(R.layout.fragment_splash, container, false))
        return binding.root
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}