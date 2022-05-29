package com.project.usereditorapp.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.usereditorapp.framework.UseCases
import com.project.usereditorapp.presentation.details.UserDetailsViewModel
import com.project.usereditorapp.presentation.list.UsersListViewModel

object UsersViewModelFactory : ViewModelProvider.Factory {

  lateinit var application: Application

  lateinit var useCases: UseCases

  fun inject(application: Application, useCases: UseCases) {
    UsersViewModelFactory.application = application
    UsersViewModelFactory.useCases = useCases
  }

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(UsersListViewModel::class.java.isAssignableFrom(modelClass)) {
      return modelClass.getConstructor(Application::class.java, UseCases::class.java)
          .newInstance(
              application,
              useCases)
    }else if(SharedViewModel::class.java.isAssignableFrom(modelClass)) {
      return modelClass.getConstructor(Application::class.java, UseCases::class.java)
        .newInstance(
          application,
          useCases)
    } else if(UserDetailsViewModel::class.java.isAssignableFrom(modelClass)) {
      return modelClass.getConstructor(Application::class.java, UseCases::class.java)
        .newInstance(
          application,
          useCases)
    }else {
      throw IllegalStateException("ViewModel must extend MajesticViewModel")
    }
  }

}