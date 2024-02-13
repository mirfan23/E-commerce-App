package com.example.core.remote

import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProductResponse
import com.example.core.remote.data.ProductReviewResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.safeApiCall
import com.example.core.remote.data.RegisterRequest
import kotlinx.coroutines.flow.Flow
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

    suspend fun fetchUploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): ProfileResponse {
        return safeApiCall { apiEndPoint.fetchProfile(userName, userImage) }
    }

    suspend fun fetchDetailProduct(
        id: String? = null
    ): DetailProductResponse {
        return safeApiCall {
            apiEndPoint.fetchDetailProduct(
                id
            )
        }
    }

    suspend fun fetchProductReview(
        id: String? = null
    ): ProductReviewResponse {
        return  safeApiCall {
            apiEndPoint.fetchReviewProduct(
                id
            )
        }
    }
}