package com.samirmaciel.techquiz.view.main.auth.register.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.view.main.auth.register.model.ImageProfileDefault

class ImageProfileDefaultRecyclerAdapter(val itemList: List<ImageProfileDefault>, val onSelectImage: (Int) -> Unit) : RecyclerView.Adapter<ImageProfileDefaultRecyclerAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imageprofile_item_recyclerview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bindItem(position)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    inner class MyViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(position: Int){
            val imageProfile = itemView.findViewById<ImageView>(R.id.iv_imageDefault)

            imageProfile.setBackgroundResource(if(itemList[position].isSelected) R.drawable.bg_selected_imageprofiledefault else R.color.purple_background)
            imageProfile.setImageResource(itemList[position].resourceID)
            imageProfile.setOnClickListener {
                onSelectImage(itemList[position].resourceID)
                    imageProfile.setBackgroundResource(R.drawable.bg_selected_imageprofiledefault)
                    itemList[position].isSelected = true
                    for(image in itemList){
                        if(image != itemList[position]){
                            image.isSelected = false
                        }
                    }
                    notifyDataSetChanged()
                }


            }

        }

}


