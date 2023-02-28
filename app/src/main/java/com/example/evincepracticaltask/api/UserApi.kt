package com.example.evincepracticaltask.api

import com.example.evincepracticaltask.data_classes.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/api/users")
    fun getUsers(@Query("page") page : Int) : Call<UserResponse>

}