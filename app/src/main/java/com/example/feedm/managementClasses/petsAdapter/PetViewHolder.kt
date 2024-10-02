package com.example.feedm.managementClasses.petsAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feedm.R

class PetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val imageView : ImageView = itemView.findViewById(R.id.pa_imgItem_RecyclerView)
    val name: TextView = itemView.findViewById(R.id.pa_nameItem_Recyclerview)
}