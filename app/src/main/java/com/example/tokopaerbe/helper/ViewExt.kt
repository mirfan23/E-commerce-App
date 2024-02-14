package com.example.tokopaerbe.helper

import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.materialswitch.MaterialSwitch

fun MaterialSwitch.checkIf(state: Boolean) {
    this.isChecked = state
}

fun View.visibleIf(state: Boolean) {
    this.isVisible = state
}

