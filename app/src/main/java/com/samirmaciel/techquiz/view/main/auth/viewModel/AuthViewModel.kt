package com.samirmaciel.techquiz.view.main.auth.viewModel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.samirmaciel.techquiz.domain.model.User
import java.io.ByteArrayOutputStream


class AuthViewModel(
    val mAuth: FirebaseAuth,
    val mDataBase: DatabaseReference,
    val mStored: FirebaseStorage
) : ViewModel() {

    var currentUser: MutableLiveData<User> = MutableLiveData()
    var onCompletedLogin: MutableLiveData<Boolean> = MutableLiveData()
    var onCompletedRegister: MutableLiveData<Pair<Boolean, String>> = MutableLiveData()


    fun loginWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            it.addOnSuccessListener {
                onCompletedLogin.postValue(true)
            }

            it.addOnFailureListener {
                onCompletedLogin.postValue(false)
            }
        }
    }

    fun registerUser(
        email: String,
        nickName: String,
        fullName: String,
        avatar: Drawable,
        password: String
    ) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            it.addOnSuccessListener {
                val userFireBase = it.user

                if(userFireBase != null){
                    val resultUpload = uploadImageToFireStored(avatar, userFireBase.uid)

                    if(resultUpload.first){
                        val user = User(userFireBase.uid, nickName, fullName, resultUpload.second!!)
                    }else{
                        onCompletedRegister.postValue(Pair(false, "Upload image fail"))
                    }
                }else{
                    onCompletedRegister.postValue(Pair(false, "create userFireBase fail"))
                }
            }

            it.addOnFailureListener {

            }
        }

    }

    private fun uploadImageToFireStored(image: Drawable, uuID: String): Pair<Boolean, String?> {
        val mountainsRef: StorageReference = mStored.reference.child("$uuID.jpg")
        val imageBitmap = (image as BitmapDrawable).bitmap

        var result = false
        var imageurl: String? = null

        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data: ByteArray = baos.toByteArray()

        val uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnCompleteListener {

            it.addOnSuccessListener {
                it.metadata?.let {
                    it.path?.let {
                        result = true
                        imageurl = it.toUri().toString()
                    }
                }
            }

            it.addOnFailureListener {
                result = false
            }
        }

        return Pair(result, imageurl)
    }

    private fun registerUserOnRealTime(user: User) {
        mDataBase.child("users").child(user.UUID).setValue(user)
    }


    class AuthViewModelFactory(
        val mAuth: FirebaseAuth,
        val mDataBase: DatabaseReference,
        val mStored: FirebaseStorage
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AuthViewModel(mAuth, mDataBase, mStored) as T
        }
    }
}