package com.project.usereditorapp.presentation.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.core.domain.User
import com.project.usereditorapp.framework.UseCases

class UserDetailsViewModel(application: Application, private val useCases: UseCases): AndroidViewModel(application) {
    val _state = MutableLiveData<UserDetailsState>(UserDetailsState())
    val state: LiveData<UserDetailsState> = _state

    init {
        _state.postValue(UserDetailsState())
    }

    fun getUserPosts(user: User) {

    }
}