package com.example.core.remote.service

import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProductResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    @POST("register")
    suspend fun fetchRegister(@Body request: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun fetchLogin(@Body request: LoginRequest): LoginResponse

    @POST("refresh")
    suspend fun fetchRefreshToken(@Body request: RefreshTokenRequest): RefreshTokenResponse

    @POST("profile")
    @Multipart
    suspend fun fetchProfile(
        @Part("userName") userName: RequestBody,
        @Part userImage: MultipartBody.Part
    ): ProfileResponse

    @POST("products")
    suspend fun fetchProduct(
        @Query("search") search: String? = null,
        @Query("brand") brand: String? = null,
        @Query("lowest") lowestPrice: Int? = null,
        @Query("highest") highestPrice: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("limit") limitItem: Int? = null,
        @Query("page") page: Int? = null
    ): ProductResponse

    @GET("products/{id}")
    suspend fun fetchDetailProduct(
        @Path("id") id: String? = null
    ): DetailProductResponse
}