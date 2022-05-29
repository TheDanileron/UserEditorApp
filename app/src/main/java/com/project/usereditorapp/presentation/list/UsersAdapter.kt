package com.project.usereditorapp.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.core.domain.User
import com.project.usereditorapp.R
import com.project.usereditorapp.databinding.UserItemBinding

class UsersAdapter(val onClick: (user: User) -> Unit): RecyclerView.Adapter<UserViewHolder>() {

    val users = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
        holder.binding.root.setOnClickListener {
            onClick(users[position])
        }
    }

    override fun getItemCount(): Int = users.size

    fun setUsers(update: List<User>) {
        users.clear()
        users.addAll(update)
        notifyDataSetChanged()
    }

    fun updateItem(user: User) {
        var i = 0
        while (i < users.size) {
            if(users[i].id == user.id) {
                users[i] = user
                notifyItemChanged(i)
                break
            }
            i++
        }
    }

    fun deleteItem(user: User) {

    }
}