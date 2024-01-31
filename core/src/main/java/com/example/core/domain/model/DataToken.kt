package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataToken (
    var accessToken: String = "",
    var expiresAt: Int = 0,
    var refreshToken: String = ""
): Parcelable