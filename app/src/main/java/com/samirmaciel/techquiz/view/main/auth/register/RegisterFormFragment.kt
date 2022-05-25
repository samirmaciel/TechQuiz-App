package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentRegisterFormBinding

class RegisterFormFragment : Fragment() {

    private var _binding: FragmentRegisterFormBinding? = null
    private val binding: FragmentRegisterFormBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterFormBinding.bind(inflater.inflate(R.layout.fragment_register_form, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}