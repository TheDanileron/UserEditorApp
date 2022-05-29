package com.project.usereditorapp.framework.data

import com.project.core.data.UserDataSource
import com.project.core.domain.User
import com.project.usereditorapp.framework.remote.UsersApiService
import com.project.usereditorapp.framework.util.JsonConverter
import java.lang.Exception

class ApiDataSource: UserDataSource {

    override suspend fun delete(id: Int): Result<Boolean> {
        val response = UsersApiService.getInstance().delete(id.toString()).execute()
        return if(response.isSuccessful){
            Result.success(true)
        } else {
            Result.failure(Exception(response.message()))
        }

    }

    override suspend fun update(user: User): Result<User?> {
        val response = UsersApiService.getInstance().update(user.id.toString(), user).execute()
        return if(response.isSuccessful){
            Result.success(response.body())
        } else {
            Result.failure(Exception(JsonConverter.convertUpdateError(response.errorBody()?.string() ?: "")))
        }
    }

    override suspend fun getList(): Result<List<User>> {
        val response = UsersApiService.getInstance().getUsers().execute()
        return if(response.isSuccessful){
            Result.success(response.body() ?: mutableListOf())
        } else {
            Result.failure(Exception(response.message()))
        }
    }
}