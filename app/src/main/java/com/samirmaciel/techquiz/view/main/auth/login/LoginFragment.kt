package com.samirmaciel.techquiz.view.main.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentLoginBinding
import com.samirmaciel.techquiz.view.challenge.quizFinal.EndGameBottomSheet
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel: AuthViewModel by sharedViewModel()
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
        setObservers()
    }

    private fun setListeners(){
        binding.btnLoginEnter.setOnClickListener {
            if(validateFields()){
                viewModel.loginWithEmailAndPassword(binding.edtLoginEmail.text.toString(),
                                                    binding.edtLoginPassword.text.toString())
            }else{
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLoginCreateNewAccount.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setObservers(){

        viewModel.isUserLogged.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        viewModel.onCompletedLogin.observe(viewLifecycleOwner){
            if(it.first){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                if(it.second != null){
                    Toast.makeText(requireContext(), it.second, Toast.LENGTH_LONG).show()
                }

            }
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