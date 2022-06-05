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
import com.samirmaciel.techquiz.domain.enums.StageOfRegister
import com.samirmaciel.techquiz.repository.DefaultAssetsData
import com.samirmaciel.techquiz.view.main.auth.register.adapter.ImageProfileDefaultRecyclerAdapter
import com.samirmaciel.techquiz.view.main.auth.register.model.ImageProfileDefault
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ImageSelectFragment : Fragment() {

    private var _binding: FragmentImageSelectProfileBinding? = null
    private val binding: FragmentImageSelectProfileBinding get() = _binding!!
    lateinit var imageProfileAdapter : ImageProfileDefaultRecyclerAdapter
    private val viewModel: AuthViewModel by sharedViewModel()

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
        setObservers()
        setDefaultProfileImage()
    }

    private fun initRecycler(){
        val imageDefaultList = DefaultAssetsData.getAllImageProfileDeafult()
        imageProfileAdapter = ImageProfileDefaultRecyclerAdapter(imageDefaultList) { drawableID ->
            binding.ivSelectedImageProfile.setImageResource(drawableID)
            viewModel.userTempRegister?.let { userForm ->
                userForm.avatar = resources.getDrawable(drawableID)
            }
        }
        binding.rvImageprofiledefault.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = imageProfileAdapter
        }
    }

    private fun setObservers(){

        viewModel.stageButtonListener.observe(viewLifecycleOwner){
            if(it.ordinal == StageOfRegister.STAGE2.ordinal){
                viewModel.userTempRegister?.let { userForm ->
                    if(userForm.avatar != null){
                        viewModel.createNewAccount(userForm)
                    }else{
                        Toast.makeText(requireContext(), "Avatar is null", Toast.LENGTH_LONG).show()
                    }

                }

            }
        }
    }

    private fun setDefaultProfileImage(){
        viewModel.userTempRegister?.let {
            it.avatar = resources.getDrawable(R.drawable.default_avatar_1)
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}