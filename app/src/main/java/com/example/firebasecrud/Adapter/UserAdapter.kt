package com.example.firebasecrud.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasecrud.Model.UserModel
import com.example.firebasecrud.R

class UserAdapter(private val list: List<UserModel>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.itemView.apply {
            var tvUser = findViewById<TextView>(R.id.tvUserName)
            tvUser.text = list[position].userName
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}