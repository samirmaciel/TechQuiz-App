package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentImageSelectProfileBinding

class ImageSelectFragment : Fragment() {

    private var _binding: FragmentImageSelectProfileBinding? = null
    private val binding: FragmentImageSelectProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSelectProfileBinding.bind(inflater.inflate(R.layout.fragment_image_select_profile, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}