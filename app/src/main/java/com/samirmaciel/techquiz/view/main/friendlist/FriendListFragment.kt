package com.samirmaciel.techquiz.view.main.friendlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentFriendListBinding
import com.samirmaciel.techquiz.view.main.friendlist.adapter.FriendListRecyclerAdapter
import com.samirmaciel.techquiz.view.main.viewModel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FriendListFragment : Fragment() {

    private var _binding: FragmentFriendListBinding? = null
    private val binding: FragmentFriendListBinding get() = _binding!!
    private val viewModel: SharedViewModel by sharedViewModel()
    lateinit var recyclerAdapter : FriendListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendListBinding.bind(inflater.inflate(R.layout.fragment_friend_list, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        initRecyclerView()
    }

    private fun setListener(){
        binding.btnFriendListAddFriend.setOnClickListener {
                viewModel.sendFriendRequest(binding.edtFriendsSearch.text.toString())
        }

        binding.btnFriendsBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setObservers(){
        viewModel.friends.observe(viewLifecycleOwner){
            recyclerAdapter.itemList = it.toMutableList()
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView(){
        recyclerAdapter = FriendListRecyclerAdapter()
        binding.rvFriendsFriends.adapter = recyclerAdapter
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}