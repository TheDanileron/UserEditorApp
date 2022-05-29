package com.project.usereditorapp.framework

import com.project.core.use_cases.DeleteUser
import com.project.core.use_cases.GetUsers
import com.project.core.use_cases.UpdateUser

data class UseCases(
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser,
    val getUsers: GetUsers,
)