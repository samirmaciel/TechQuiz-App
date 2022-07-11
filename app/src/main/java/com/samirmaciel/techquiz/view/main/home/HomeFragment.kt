package com.samirmaciel.techquiz.view.main.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentHomeBinding
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import com.samirmaciel.techquiz.view.main.viewModel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()

    }

    private fun setListeners(){

        binding.btnHomeNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationsFragment)
        }
        binding.btnHomeLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Deseja deslogar do game?")
                setPositiveButton(
                    "Sim"
                ) { p0, p1 ->
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    FirebaseAuth.getInstance().signOut()

                }
                setNegativeButton("Não", null)
            }.create().show()

        }

        binding.tvHomeOnlineFriends.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_friendListFragment)
        }
    }

    private fun setObservers(){
        viewModel.notifications.observe(viewLifecycleOwner){
            val newNotifications = viewModel.getNewNotificationsCount(it)
            if(newNotifications > 0){
                binding.tvHomeNotificationCount.text = newNotifications.toString()
                binding.tvHomeNotificationCount.visibility = View.VISIBLE
            }else{
                binding.tvHomeNotificationCount.visibility = View.INVISIBLE
            }

        }

        viewModel.userInfo.observe(viewLifecycleOwner){

        }

        viewModel.onErrorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }

}