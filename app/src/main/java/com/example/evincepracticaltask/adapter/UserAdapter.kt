package com.example.evincepracticaltask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.evincepracticaltask.data_classes.DataItem
import com.example.evincepracticaltask.databinding.UserRowItemBinding
import com.example.evincepracticaltask.listener.OnItemClickListener

class UserAdapter(private val list: ArrayList<DataItem>,val context: Context,val listener : OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: UserRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding : UserRowItemBinding = UserRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = list[position]
        holder.binding.obj = data

        Glide.with(context).load(data.avatar).into(holder.binding.profile)

        holder.binding.btnDelete.setOnClickListener {
            listener.onItemClick(position,it)
        }
        holder.binding.btnUpdate.setOnClickListener {
            listener.onItemClick2(position,it)
        }
    }
    override fun getItemCount(): Int = list.size
}