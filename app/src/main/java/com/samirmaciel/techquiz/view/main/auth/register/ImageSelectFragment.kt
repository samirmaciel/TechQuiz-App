package com.samirmaciel.techquiz.view.main.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.FragmentImageSelectProfileBinding
import com.samirmaciel.techquiz.databinding.ImageprofileItemRecyclerviewBinding
import com.samirmaciel.techquiz.repository.DefaultAssetsData
import com.samirmaciel.techquiz.view.main.auth.register.adapter.ImageProfileDefaultRecyclerAdapter
import com.samirmaciel.techquiz.view.main.auth.register.model.ImageProfileDefault

class ImageSelectFragment : Fragment() {

    private var _binding: FragmentImageSelectProfileBinding? = null
    private val binding: FragmentImageSelectProfileBinding get() = _binding!!
    lateinit var imageProfileAdapter : ImageProfileDefaultRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageSelectProfileBinding.bind(inflater.inflate(R.layout.fragment_image_select_profile, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }


    private fun initRecycler(){
        val imageDefaultList = DefaultAssetsData.getAllImageProfileDeafult()
        imageProfileAdapter = ImageProfileDefaultRecyclerAdapter(imageDefaultList, {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
        })
        binding.rvImageprofiledefault.apply {
            layoutManager = GridLayoutManager(requireContext(), 5)
            adapter = imageProfileAdapter
        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}