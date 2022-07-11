package com.samirmaciel.techquiz.view.main.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentNotificationsBinding
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import com.samirmaciel.techquiz.view.main.notifications.adapter.NotificationRecyclerAdapter
import com.samirmaciel.techquiz.view.main.viewModel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NotificationsFragment : Fragment() {

    private var _binding : FragmentNotificationsBinding? = null
    private val binding : FragmentNotificationsBinding get() = _binding!!
    lateinit var recyclerAdapter : NotificationRecyclerAdapter
    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.bind(inflater.inflate(R.layout.fragment_notifications, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        setObservers()
        setListeners()
    }

    private fun initRecycler(){
        recyclerAdapter = NotificationRecyclerAdapter{
            viewModel.onInteractWithNotification(it)
        }
        binding.rvNotifications.adapter = recyclerAdapter
    }

    private fun setListeners(){
        binding.btnNotificationsBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setObservers(){

        viewModel.notifications.observe(viewLifecycleOwner){
            recyclerAdapter.itemList = it.toMutableList()
            if(viewModel.getNewNotificationsCount(it) > 0){
                viewModel.setAllNotificationsVizualided(it)
            }
            recyclerAdapter.notifyDataSetChanged()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}