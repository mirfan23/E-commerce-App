package com.example.core.domain.model

import android.os.Parcelable
import android.support.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataProfile (
    var userName: String = "",
    var userImage: String = ""
): Parcelable