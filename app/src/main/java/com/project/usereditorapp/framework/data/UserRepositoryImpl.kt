package com.project.usereditorapp.framework.data

import com.project.core.data.UserDataSource
import com.project.core.data.UserRepository
import com.project.core.domain.User

class UserRepositoryImpl(val remoteDataSource: UserDataSource): UserRepository {
    override suspend fun delete(id: Int): Result<Boolean> {
        return remoteDataSource.delete(id)
    }

    override suspend fun update(user: User): Result<User?> {
        return remoteDataSource.update(user)
    }

    override suspend fun getList(): Result<List<User>> {
        return remoteDataSource.getList()
    }
}