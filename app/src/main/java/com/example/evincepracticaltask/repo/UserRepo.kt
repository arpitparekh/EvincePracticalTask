package com.example.evincepracticaltask.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evincepracticaltask.api.RetrofitService
import com.example.evincepracticaltask.api.UserApi
import com.example.evincepracticaltask.data_classes.DataItem
import com.example.evincepracticaltask.data_classes.UserResponse
import com.example.evincepracticaltask.database.UserDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo(
    private val noteDatabase: UserDatabase
) {

    private var userListResponse = MutableLiveData<UserResponse>()
    lateinit var api : UserApi

     fun insertUser(user: DataItem) = noteDatabase.getUserDao().insertUser(user)

     fun updateUser(user: DataItem) = noteDatabase.getUserDao().updateUser(user)

     fun deleteUser(user: DataItem) = noteDatabase.getUserDao().deleteUser(user)

    fun getAllUsers(): LiveData<List<DataItem>> = noteDatabase.getUserDao().getAllUsers()

    fun check(id :Int): LiveData<Boolean> = noteDatabase.getUserDao().isRowIsExist(id)

    fun getUserData(page : Int) : MutableLiveData<UserResponse>{
        api = RetrofitService.getInterface()
        val call = api.getUsers(page)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
               userListResponse.value = response.body()
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userListResponse.postValue(null)
            }
        })

        return userListResponse
    }

}