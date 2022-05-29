package com.project.usereditorapp.presentation

import com.project.core.domain.User

data class SharedState(
    val selectedUser: Pair<User, EntityAction>?,
    val message: String
) {
}