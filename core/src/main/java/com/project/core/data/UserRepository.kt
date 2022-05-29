package com.project.core.data

import com.project.core.domain.User

interface UserRepository {
    suspend fun delete(id: Int): Result<Boolean>

    suspend fun update(user: User): Result<User?>

    suspend fun getList(): Result<List<User>>
}