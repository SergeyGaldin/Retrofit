package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recucler_row_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var textViewName: TextView
        private var textViewEmail: TextView
        private var textViewStatus: TextView

        init {
            textViewName = itemView.findViewById(R.id.textViewName)
            textViewEmail = itemView.findViewById(R.id.textViewEmail)
            textViewStatus = itemView.findViewById(R.id.textViewStatus)
        }

        fun bind(data: User) {
            textViewName.text = data.name
            textViewEmail.text = data.email
            textViewStatus.text = data.status
        }
    }
}