package com.example.evincepracticaltask.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {

    companion object{
        private const val BASE_URL = "https://reqres.in/"
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInterface(): UserApi {
            return retrofit.create(UserApi::class.java)
        }

    }

}