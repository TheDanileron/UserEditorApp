package com.project.usereditorapp.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.core.domain.User
import com.project.usereditorapp.framework.util.Const
import com.project.usereditorapp.framework.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel(application: Application, private val useCases: UseCases) :
    AndroidViewModel(application) {
    val _state = MutableLiveData<UsersListState>(UsersListState())
    val state: LiveData<UsersListState> = _state

    fun loadUsers() {
        _state.value?.userList?.let {
            if (it.isNotEmpty())
                return
            viewModelScope.launch(Dispatchers.IO) {
                val result = useCases.getUsers()
                if (result.isSuccess) {
                    _state.postValue(_state.value?.copy(userList = result.getOrDefault(emptyList())))
                } else {
                    val message = result.exceptionOrNull()?.message ?: ""
                    _state.postValue(_state.value?.copy(message = message))
                }
            }
        }
    }

    fun consumeMessage() {
        _state.postValue(_state.value?.copy(message = ""))
    }

    fun updateSelectedUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            var i = 0
            val users = _state.value?.userList?.toMutableList() ?: mutableListOf()
            while (i < users.size) {
                if (users[i].id == user.id) {
                    users[i] = user
                    break
                }
                i++
            }
            _state.postValue(
                _state.value?.copy(
                    message = Const.SUCCESS_CODE,
                    userList = users,
                )
            )
        }
    }

fun deleteSelectedUser(user: User) {
    viewModelScope.launch(Dispatchers.IO) {
        var i = 0
        val users = _state.value?.userList?.toMutableList() ?: mutableListOf()
        while (i < users.size) {
            if (users[i].id == user.id) {
                break
            }
            i++
        }
        users.removeAt(i)
        _state.postValue(_state.value?.copy(message = Const.SUCCESS_CODE, userList = users))
    }
}

}