package com.samirmaciel.techquiz.view.main.friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentFriendListBinding


class FriendListFragment : Fragment() {

    private var _binding: FragmentFriendListBinding? = null
    private val binding: FragmentFriendListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendListBinding.bind(inflater.inflate(R.layout.fragment_friend_list, container, false))
        return binding.root
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}