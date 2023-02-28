package com.example.evincepracticaltask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evincepracticaltask.repo.UserRepo

class UserViewModelFactory(
    private val repository: UserRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        try {
            val constructor = modelClass.getDeclaredConstructor(UserRepo::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {

        }
        return super.create(modelClass)
    }
}