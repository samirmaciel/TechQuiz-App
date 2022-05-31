package com.samirmaciel.techquiz.view.main.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentLoginBinding
import com.samirmaciel.techquiz.view.challenge.quizFinal.EndGameBottomSheet

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginEnter.setOnClickListener {
            val modal = EndGameBottomSheet()

            modal.show(childFragmentManager, "modal")
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}