package com.project.usereditorapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.core.domain.User
import com.project.usereditorapp.framework.UseCases
import com.project.usereditorapp.framework.util.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application, private val useCases: UseCases): AndroidViewModel(application) {
    private val _state = MutableLiveData<SharedState>()
    val state: LiveData<SharedState> = _state

    init {
        _state.postValue(
            SharedState(
            null,
            ""
        )
        )
    }

    val selectedUserName: String
        get() {
            return _state.value?.selectedUser?.first?.name ?: ""
        }

    val selectedUserEmail: String
        get() {
            return _state.value?.selectedUser?.first?.email ?: ""
        }

    fun userSelected(user: User) {
        _state.postValue(_state.value?.copy(
            selectedUser = Pair(user, EntityAction.VIEW),
            message = ""
        ))
    }

    fun updateSelectedUser(name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO){
            val updated = _state.value?.selectedUser?.first?.copy(
                name = name,
                email = email)
            updated?.let {
                val result = useCases.updateUser(updated)
                if(result.isSuccess) {
                    _state.postValue(_state.value?.copy(
                        selectedUser = Pair(updated, EntityAction.UPDATE),
                        Const.SUCCESS_CODE)
                    )
                } else {
                    _state.postValue(_state.value?.copy(
                        message = result.exceptionOrNull()?.message ?: Const.FAILED_TO_UPDATE)
                    )
                }
            }
        }
    }

    fun deleteSelectedUser() {
        viewModelScope.launch(Dispatchers.IO){

            _state.value?.selectedUser?.first?.let {
                val result = useCases.deleteUser(it.id)
                if(result.isSuccess) {
                    _state.postValue(_state.value?.copy(
                        selectedUser = Pair(it, EntityAction.DELETE),
                        Const.SUCCESS_CODE)
                    )
                } else {
                    _state.postValue(_state.value?.copy(
                        message = result.exceptionOrNull()?.message ?: Const.FAILED_TO_UPDATE)
                    )
                }
            }
        }
    }
}