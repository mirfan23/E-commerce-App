package com.example.core.remote

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProductResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.safeApiCall
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RemoteDataSource(private val apiEndPoint: ApiEndPoint) {
    suspend fun fetchLogin(
        request: LoginRequest
    ): LoginResponse {
        return safeApiCall { apiEndPoint.fetchLogin(request) }
    }

    suspend fun fetchRegister(
        request: RegisterRequest
    ): RegisterResponse {
        return safeApiCall { apiEndPoint.fetchRegister(request) }
    }

    suspend fun fetchRefreshToken(
        request: RefreshTokenRequest
    ): RefreshTokenResponse {
        return safeApiCall { apiEndPoint.fetchRefreshToken(request) }
    }

    suspend fun fetchUploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): ProfileResponse {
        return safeApiCall { apiEndPoint.fetchProfile(userName, userImage) }
    }

    suspend fun fetchProduct(
        search: String? = null,
        brand: String? = null,
        lowestPrice: Int? = null,
        highestPrice: Int? = null,
        sort: String? = null,
        limitItem: Int? = null,
        page: Int? = null
    ): ProductResponse {
        return safeApiCall {
            apiEndPoint.fetchProduct(
                search,
                brand,
                lowestPrice,
                highestPrice,
                sort,
                limitItem,
                page
            )
        }
    }
}