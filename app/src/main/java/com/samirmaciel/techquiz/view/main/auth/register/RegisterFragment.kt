package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!
    private var registerStep = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.bind(inflater.inflate(R.layout.fragment_register, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegisterContinue.text = "${getText(R.string.title_register_button_continue)} $registerStep/2"
        binding.btnRegisterContinue.setOnClickListener {
            if(registerStep < 2) {
                registerStep += 1
                binding.btnRegisterContinue.text =
                    "Finalizar $registerStep/2"
                binding.fragmentContainerRegister.findNavController()
                    .navigate(R.id.action_registerFormFragment_to_imageSelectFragment)
            }else{
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}