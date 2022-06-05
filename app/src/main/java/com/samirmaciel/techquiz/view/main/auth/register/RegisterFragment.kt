package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentRegisterBinding
import com.samirmaciel.techquiz.domain.enums.StageOfRegister
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!
    private var registerStep = 1
    private val viewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.bind(inflater.inflate(R.layout.fragment_register, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.btnRegisterContinue.text = "${getText(R.string.title_register_button_continue)} $registerStep/2"
        binding.btnRegisterContinue.setOnClickListener {
            if(viewModel.stageRegister == null){
                viewModel.stageButtonListener.value = StageOfRegister.STAGE1
            }else if(viewModel.stageRegister == StageOfRegister.STAGE1){
                viewModel.stageButtonListener.value = StageOfRegister.STAGE2
            }

        }
    }

    private fun setObservers(){
        viewModel.isFieldValid.observe(viewLifecycleOwner){
                if (it.first){
                    changeStageOfRegistration()
                }else{
                    Toast.makeText(requireContext(), it.second, Toast.LENGTH_LONG).show()
                }
        }

        viewModel.onCompletedRegister.observe(viewLifecycleOwner){
            if(it.first){
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(), it.second, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun changeStageOfRegistration() {
        registerStep += 1
        viewModel.stageRegister = StageOfRegister.STAGE1
        binding.btnRegisterContinue.text =
            "Finalizar $registerStep/2"
        binding.fragmentContainerRegister.findNavController()
            .navigate(R.id.action_registerFormFragment_to_imageSelectFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}