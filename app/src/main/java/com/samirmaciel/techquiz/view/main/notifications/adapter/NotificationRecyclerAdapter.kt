package com.samirmaciel.techquiz.view.main.notifications.adapter

import android.os.strictmode.UntaggedSocketViolation
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.domain.model.Notification

class NotificationRecyclerAdapter(val onInteract: (Pair<Notification, String>) -> Unit) :
    RecyclerView.Adapter<NotificationRecyclerAdapter.MyViewHolder>() {

    var itemList: MutableList<Notification> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(position)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNotificationRequisitionNickName = itemView.findViewById<TextView>(R.id.tv_notificationRequestUserName)

        val btnDecline = itemView.findViewById<Button>(R.id.btn_notificationDecline)

        fun bindItem(itemPosition: Int){
            val btnAccept = itemView.findViewById<Button>(R.id.btn_notificationAccept)
            tvNotificationRequisitionNickName.text = itemList[itemPosition].userRequisitionName
            Log.d("TESTE", "bindItem: 1111")
            btnAccept.setOnClickListener {
                Log.d("TESTE", "bindItem: ")
                onInteract(Pair(itemList[itemPosition], ACCEPT_FRIEND_REQUISITION))
                itemList.remove(itemList[itemPosition])
                notifyDataSetChanged()
            }

            btnDecline.setOnClickListener {
                onInteract(Pair(itemList[itemPosition], DECLINE_FRIEND_REQUISITION))
                itemList.remove(itemList[itemPosition])
                notifyDataSetChanged()
            }
        }

    }

    companion object {
        const val ACCEPT_FRIEND_REQUISITION = "ACCEPT_FRIEND"
        const val DECLINE_FRIEND_REQUISITION = "DECLINE_FRIEND"
    }

}