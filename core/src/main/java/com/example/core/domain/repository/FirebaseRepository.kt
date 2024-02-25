package com.example.core.domain.repository

import android.os.Bundle
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun logScreenView(screenName: String)
    fun logEvent(eventName: String, bundle: Bundle)
    fun getConfigStatusUpdate(): Flow<Boolean>
    fun getConfigPayment(): String
}