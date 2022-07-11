package com.samirmaciel.techquiz.view.main.friendlist.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.domain.model.FriendUser

class FriendListRecyclerAdapter: RecyclerView.Adapter<FriendListRecyclerAdapter.MyViewModel>() {

    var itemList = mutableListOf<FriendUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friends_item, parent, false)
        return MyViewModel(view)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvFriendNickName = itemView.findViewById<TextView>(R.id.tv_friendItemPlayerName)

        fun bindItem(itemPosition: Int){
            tvFriendNickName.text = itemList[itemPosition].nickName
        }

    }

}