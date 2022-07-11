package com.samirmaciel.techquiz.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.samirmaciel.techquiz.view.main.auth.viewModel.AuthViewModel
import com.samirmaciel.techquiz.view.main.viewModel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


val mainModule = module {


        viewModel {
            AuthViewModel(
                FirebaseAuth.getInstance(),
                FirebaseDatabase.getInstance().reference,
                FirebaseStorage.getInstance().getReference(),
                FirebaseFirestore.getInstance()
            )
        }

    viewModel {
        SharedViewModel(
            FirebaseAuth.getInstance(),
            FirebaseDatabase.getInstance().reference,
            FirebaseStorage.getInstance().getReference(),
            FirebaseFirestore.getInstance()
        )
    }



}