package com.samirmaciel.techquiz.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.DialogFragmentProfileBinding

class ProfileDialogFragment: DialogFragment() {

    private var _binding: DialogFragmentProfileBinding? = null
    private val binding: DialogFragmentProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFragmentProfileBinding.bind(inflater.inflate(R.layout.dialog_fragment_profile, container, false))
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}