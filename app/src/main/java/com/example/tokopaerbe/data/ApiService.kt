package com.example.tokopaerbe.data

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("http://172.17.20.230:5000/register")
    fun registerUser(@Body requestBody: RequestBody): Call<RegisterData>
}
