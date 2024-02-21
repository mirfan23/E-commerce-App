package com.example.core.domain.repository

import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseRepositoryImpl(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val remoteConfig: FirebaseRemoteConfig
) : FirebaseRepository {
    override fun logScreenView(screenName: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun logEvent(eventName: String, bundle: Bundle) {
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    override fun getConfigStatusUpdate(): Flow<Boolean> =
        callbackFlow {
            remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                  if (configUpdate.updatedKeys.contains("payment_list")) {
                      remoteConfig.activate().addOnCompleteListener { task ->
                          trySend(task.isSuccessful)
                      }
                  }
                }
                override fun onError(error: FirebaseRemoteConfigException) {
                    Log.d("MASUK : ", "Dynamic Remote Config Error: $error")
                    trySend(false)
                }
            })
            awaitClose()
        }

    override fun getConfigPayment(): String = remoteConfig.getString("payment_list")
}