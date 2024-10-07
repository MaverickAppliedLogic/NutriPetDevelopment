package com.example.feedm.ui.view.managementClasses.resultAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feedm.R

class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val imageView : ImageView = itemView.findViewById(R.id.sa_imgItem_RecyclerView)
    val title : TextView = itemView.findViewById(R.id.sa_titleItem_Recyclerview)
}