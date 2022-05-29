package com.project.usereditorapp.presentation.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.core.domain.User
import com.project.usereditorapp.R
import com.project.usereditorapp.databinding.UserItemBinding

class UserViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.title.text = user.name
        binding.subTitle.text = user.email
    }
}