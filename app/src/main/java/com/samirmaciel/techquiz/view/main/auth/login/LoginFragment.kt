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
        setListeners()

    }

    private fun setListeners(){
        binding.btnLoginEnter.setOnClickListener {
        }

        binding.tvLoginCreateNewAccount.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateFields(): Boolean {
        val email = binding.edtLoginEmail.text.toString()
        val password = binding.edtLoginPassword.text.toString()

        return email.isNotBlank() && password.isNotBlank()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}