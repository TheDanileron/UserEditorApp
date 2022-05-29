package com.project.usereditorapp.framework

import android.app.Application
import com.project.core.use_cases.DeleteUser
import com.project.core.use_cases.GetUsers
import com.project.core.use_cases.UpdateUser
import com.project.usereditorapp.framework.data.ApiDataSource
import com.project.usereditorapp.framework.data.UserRepositoryImpl
import com.project.usereditorapp.presentation.UsersViewModelFactory

class UsersApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val usersDataSource = ApiDataSource()
        val repository = UserRepositoryImpl(usersDataSource)

        UsersViewModelFactory.inject(
            this,
            UseCases(
                UpdateUser(repository),
                DeleteUser(repository),
                GetUsers(repository)
            )
            )
    }
}