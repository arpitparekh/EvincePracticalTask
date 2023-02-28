package com.example.evincepracticaltask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evincepracticaltask.repo.UserRepo
import com.example.evincepracticaltask.data_classes.DataItem
import com.example.evincepracticaltask.data_classes.UserResponse

class UserViewModel(
    private val repository: UserRepo
): ViewModel() {

    private var userListResponse = MutableLiveData<UserResponse>()

     fun insertUser(user: DataItem) = repository.insertUser(user)

     fun updateUser(user: DataItem) = repository.updateUser(user)

     fun deleteUser(user: DataItem) = repository.deleteUser(user)

    suspend fun check(id :Int): LiveData<Boolean> = repository.check(id)

    fun getAllUsers() = repository.getAllUsers()

    fun getAllUsersServer(page : Int) : MutableLiveData<UserResponse>{
        userListResponse = repository.getUserData(page)
        return userListResponse
    }
}