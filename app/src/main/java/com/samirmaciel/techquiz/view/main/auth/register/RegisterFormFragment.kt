package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentRegisterFormBinding
import com.samirmaciel.techquiz.domain.enums.StageOfRegister
import com.samirmaciel.techquiz.domain.model.UserForm
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterFormFragment : Fragment() {

    private var _binding: FragmentRegisterFormBinding? = null
    private val binding: FragmentRegisterFormBinding get() = _binding!!
    private val viewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterFormBinding.bind(
            inflater.inflate(
                R.layout.fragment_register_form,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers(){

        viewModel.stageButtonListener.observe(viewLifecycleOwner){
            validateNickName()
        }

        viewModel.onCheckNickNameIfExists.observe(viewLifecycleOwner){
            if(it){
                validateAllFields(viewModel.stageButtonListener.value!!)
            }else{
                viewModel.isFieldValid.value = Pair(false, "NickName já existe")
            }
        }
    }

    private fun validateAllFields(it: StageOfRegister) {
        if (it.ordinal == StageOfRegister.STAGE1.ordinal) {
            if (validateFields()) {
                if (validatePassword()) {
                    viewModel.userTempRegister = UserForm(
                        binding.edtRegisterNickName.text.toString(),
                        binding.edtRegisterFullName.text.toString(),
                        binding.edtRegisterEmail.text.toString(),
                        binding.edtRegisterPassword.text.toString()
                    )
                    viewModel.isFieldValid.value = Pair(true, null)
                } else {
                    viewModel.isFieldValid.value = Pair(false, "Campo senha não está igual")
                }

            } else {
                viewModel.isFieldValid.value = Pair(false, "Preencha todos os campos")
            }
        }
    }

    private fun validateFields(): Boolean {
        val nickName = binding.edtRegisterNickName.text.toString()
        val fullName = binding.edtRegisterFullName.text.toString()
        val email = binding.edtRegisterEmail.text.toString()
        val password = binding.edtRegisterPassword.text.toString()
        val passwordAgain = binding.edtRegisterPasswordAgain.text.toString()

        return !nickName.isNullOrBlank() &&
                !fullName.isNullOrBlank() &&
                !email.isNullOrBlank() &&
                !password.isNullOrBlank() &&
                !passwordAgain.isNullOrBlank()
    }

    private fun validateNickName(){
       viewModel.validateNickName(binding.edtRegisterNickName.text.toString())

    }

    private fun validatePassword(): Boolean{
        val password1 = binding.edtRegisterPassword.text.toString()
        val password2 = binding.edtRegisterPasswordAgain.text.toString()

        return password1.equals(password2, true)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}