package com.samirmaciel.techquiz.view.main.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.samirmaciel.techquiz.domain.model.FriendUser
import com.samirmaciel.techquiz.domain.model.Notification
import com.samirmaciel.techquiz.domain.model.User
import com.samirmaciel.techquiz.view.main.notifications.adapter.NotificationRecyclerAdapter

class SharedViewModel(
    val mAuth: FirebaseAuth,
    val mDataBase: DatabaseReference,
    val mStored: StorageReference,
    val mStore: FirebaseFirestore
) : ViewModel() {

    val notifications: MutableLiveData<List<Notification>> = MutableLiveData()
    val friends:  MutableLiveData<List<FriendUser>> = MutableLiveData()
    val userInfo: MutableLiveData<User> = MutableLiveData()
    val onErrorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        loadUserInfo()
    }

    private fun loadFriendList(userUid: String){
        val notificationQuery = mDataBase.child("friendList|$userUid")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val friendList = mutableListOf<FriendUser>()
                for (postSnapshot in dataSnapshot.children) {
                    val friendUser = postSnapshot.getValue(FriendUser::class.java)
                    if (friendUser != null) {
                        friendList.add(friendUser)
                    }
                }
                friends.postValue(friendList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message

            }
        }
        notificationQuery.addValueEventListener(postListener)
    }

    private fun loadNotifications(userUid: String) {

        val notificationQuery = mDataBase.child("notifications|$userUid")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val notificationList = mutableListOf<Notification>()
                for (postSnapshot in dataSnapshot.children) {
                    val notification = postSnapshot.getValue(Notification::class.java)
                    if (notification != null) {
                        notificationList.add(notification)
                    }
                }

                notifications.postValue(notificationList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message

            }
        }
        notificationQuery.addValueEventListener(postListener)
    }

    private fun loadUserInfo() {
        val fireBaseUser = mAuth.currentUser

        if (fireBaseUser != null) {
            mDataBase.child("users").child(fireBaseUser.uid).get().addOnSuccessListener {
                val user = it.getValue(User::class.java)
                if (user != null) {
                    userInfo.postValue(user)

                }
                loadFriendList(fireBaseUser.uid)
                loadNotifications(fireBaseUser.uid)
            }.addOnFailureListener {
                onErrorMessage.postValue("Failure on load user info")
            }

        }else{
            onErrorMessage.postValue("Failure on load user info")
        }
    }

    private fun finalizeFriendRequest(userUid: String) {

        mDataBase.child("users").child(userUid).get().addOnSuccessListener {
            val user = it.getValue(User::class.java)

            if (user != null) {
                val newNotification = Notification(
                    userUid,
                    user.nickName,
                    userInfo.value?.UUID,
                    userInfo.value?.nickName,
                    false
                )

                userInfo.value?.UUID?.let {
                    mDataBase.child("notifications|$userUid").child(it)
                        .setValue(newNotification)
                }

            }
        }.addOnFailureListener {
            onErrorMessage.postValue("Failure on request user info for send friend request")
        }

    }

    fun sendFriendRequest(nickName: String) {
        mDataBase.child("GeneralUserInfo|NickNames").child(nickName.lowercase()).get()
            .addOnSuccessListener {
                val userUid = it.getValue(String::class.java)

                if (userUid != null) {

                    finalizeFriendRequest(userUid)
                }
            }.addOnFailureListener {
                onErrorMessage.postValue("Failure on request nickname of user for send friend request")
        }
    }

    fun setAllNotificationsVizualided(notifcationList: List<Notification>){
        for(notification in notifcationList){
            setNotificationVisualized(notification.userRequisitionUUID)
        }
        loadNotifications(userInfo.value!!.UUID)
    }

    fun setNotificationVisualized(userUID: String?){
        if(userUID != null){
            mDataBase.child("notifications|${userInfo.value!!.UUID}").child(userUID).get()
                .addOnSuccessListener {
                    val notification = it.getValue(Notification::class.java)
                    if (notification != null) {
                        notification.isVisualized = true
                        notification.isAccept = true
                        mDataBase.child("notifications|${userInfo.value!!.UUID}").child(userUID).setValue(notification).addOnFailureListener {
                            onErrorMessage.postValue("Failure on save notification of user for set visuzalized notification: " + it.message )
                        }
                    }
                }.addOnFailureListener {
                    onErrorMessage.postValue("Failure on request notification of user for set visuzalized notification")
                }
        }
    }

    fun getNewNotificationsCount(notificationList: List<Notification>): Int{
        var newNotificationsCount = 0
        for(notification in notificationList){
            if(notification.isVisualized != true){
                newNotificationsCount += 1
            }
        }

        return newNotificationsCount
    }

    fun onInteractWithNotification(notificationValues: Pair<Notification, String>){
        if(notificationValues.second.equals(NotificationRecyclerAdapter.ACCEPT_FRIEND_REQUISITION, true)){
            addFriendToMyList(notificationValues.first.userRequisitionName, notificationValues.first.userRequisitionUUID)
        }
        mDataBase.child("notifications|${userInfo.value!!.UUID}").child(notificationValues.first.userRequisitionUUID!!).removeValue()
    }

    private fun addFriendToMyList(friendNickName: String?, friendUID: String?){

        if(friendNickName != null && friendUID != null){
            val newfriend = FriendUser(uuid = friendUID, nickName = friendNickName)
            val newFriendRequisition = FriendUser(userInfo.value!!.UUID, userInfo.value!!.nickName)
            mDataBase.child("friendList|${userInfo.value!!.UUID}").child(friendNickName).setValue(newfriend).addOnFailureListener {
                onErrorMessage.postValue("Failure on save friend to my list")
            }
            mDataBase.child("friendList|${friendUID}").child(userInfo.value!!.UUID).setValue(newFriendRequisition).addOnFailureListener {
                onErrorMessage.postValue("Failure on save friend to my list")
            }
        }
    }
}