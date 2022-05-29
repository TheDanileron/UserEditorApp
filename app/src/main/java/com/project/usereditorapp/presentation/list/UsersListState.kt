package com.project.usereditorapp.presentation.list

import com.project.core.domain.User

data class UsersListState(
    val isLoading: Boolean = false,
    val userList: List<User> = emptyList(),
    val message: String = ""
    )
