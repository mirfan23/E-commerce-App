package com.example.tokopaerbe.helper

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.materialswitch.MaterialSwitch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun MaterialSwitch.checkIf(state: Boolean) {
    this.isChecked = state
}

fun View.visibleIf(state: Boolean) {
    this.isVisible = state
}

