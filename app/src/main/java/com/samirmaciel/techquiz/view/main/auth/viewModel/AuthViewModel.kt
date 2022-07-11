package com.samirmaciel.techquiz.view.main.auth.viewModel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.StorageReference
import com.samirmaciel.techquiz.domain.enums.FirebaseCollectionsNames
import com.samirmaciel.techquiz.domain.enums.StageOfRegister
import com.samirmaciel.techquiz.domain.model.User
import com.samirmaciel.techquiz.domain.model.UserForm
import java.io.ByteArrayOutputStream
import java.lang.Exception


class AuthViewModel(
    val mAuth: FirebaseAuth,
    val mDataBase: DatabaseReference,
    val mStored: StorageReference,
    val mStore: FirebaseFirestore
) : ViewModel() {

    var userTempRegister: UserForm? = null
    var stageRegister: StageOfRegister? =  null
    var stageButtonListener: MutableLiveData<StageOfRegister> = MutableLiveData()
    var isFieldValid: MutableLiveData<Pair<Boolean, String?>> = MutableLiveData()
    var onCompletedLogin: MutableLiveData<Pair<Boolean, String>> = MutableLiveData()
    var isUserLogged: MutableLiveData<Boolean> = MutableLiveData()
    var onCheckNickNameIfExists: MutableLiveData<Boolean> = MutableLiveData()
    var onCompletedRegister: MutableLiveData<Pair<Boolean, String>> = MutableLiveData()

    init {
        checkUserLoged()
    }

    fun loginWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            it.addOnSuccessListener {
                onCompletedLogin.postValue(Pair(true, "Login efetuado com sucesso"))
            }

            it.addOnFailureListener {
                onCompletedLogin.postValue(Pair(false, it.message.toString()))
            }
        }
    }

    fun createNewAccount(
        userForm: UserForm
    ) {
        mAuth.createUserWithEmailAndPassword(userForm.email, userForm.password).addOnCompleteListener {

            it.addOnSuccessListener {
                val userFireBase = it.user

                if (userFireBase != null) {
                        val user = User(userFireBase.uid, userForm.nickName, userForm.fullName)
                        saveUserOnRealTimeDatabase(user, userForm.avatar!!)

                } else {
                    onCompletedRegister.postValue(Pair(false, "create userFireBase fail"))
                }
            }

            it.addOnFailureListener {
                onCompletedRegister.postValue((Pair(false, it.message.toString())))
            }
        }

    }

    fun validateNickName(nickName: String){

        mDataBase.child("GeneralUserInfo|NickNames").child(nickName.lowercase()).get().addOnSuccessListener {
            val valueNickName: String? = it.getValue() as String?

            if(valueNickName != null){
                onCheckNickNameIfExists.postValue(false)
            }else{
                onCheckNickNameIfExists.postValue(true)
            }

        }

    }


    private fun checkUserLoged() {
        val user = mAuth.currentUser
        if (user != null) {
            isUserLogged.value = true
        }
    }

    private fun saveUserOnRealTimeDatabase(user: User, image: Drawable) {
        val mountainsRef: StorageReference = mStored.child("${user.UUID}.jpg")
        val imageBitmap = (image as BitmapDrawable).bitmap

        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data: ByteArray = baos.toByteArray()

        val uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnCompleteListener {

            it.addOnSuccessListener {

                try {
                    val mapNickNames: HashMap<String, String> = hashMapOf()
                    mapNickNames[user.nickName] = user.UUID
                    mStored.child(it.metadata!!.path.toUri().toString()).downloadUrl.addOnSuccessListener {
                        user.avatar = it.toString()
                        mDataBase.child(FirebaseCollectionsNames.USERS.label).child(user.UUID).setValue(user)
                        mDataBase.child("GeneralUserInfo|NickNames").child(user.nickName.lowercase()).setValue(user.UUID)
                        onCompletedRegister.postValue(Pair(true, "Registro efetuado com sucesso!"))
                    }

                }catch (ex: Exception){
                    onCompletedRegister.postValue(Pair(false, ex.message.toString()))
                }

            }

            it.addOnFailureListener {
                onCompletedRegister.postValue(Pair(false, it.message.toString()))
            }
        }
    }

}