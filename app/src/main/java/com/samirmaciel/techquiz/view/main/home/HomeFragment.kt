package com.samirmaciel.techquiz.view.main.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHomeLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Deseja deslogar do game?")
                setPositiveButton(
                    "Sim"
                ) { p0, p1 ->
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
                setNegativeButton("Não", null)
            }.create().show()

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }

}