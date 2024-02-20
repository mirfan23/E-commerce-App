package com.example.tokopaerbe.helper

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Base64

object Constant {
    //Profile Fragment
    const val CAMERA_PERMISSION_REQUEST_CODE = 100
    const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 101
    const val INTENT_TYPE = "image/*"
    const val ALERT_TITLE = "Permission Required"
    const val ALERT_MESSAGE = "This permission is required to access media images."
    const val EXTRAS_DATA = "data"
    const val DATE_FORMAT = "yyyyMMdd_HHmm ss"
    const val MIME_TYPE = "image/jpeg"
    const val NEGATIVE_BUTTON_TEXT = "Permission denied. Cannot proceed."

    //Onboarding Fragment
    const val SKIP_VALUE = "skip"

    //SplashScreen Fragment
    const val SKIP_KEY = "skip"
    const val ANIMATION_DELAY = 1000L
    const val ANIMATION_START = 0f
    const val RED_ROTATION = 25f
    const val RED_TRANSLATION_X = 75f
    const val RED_TRANSLATION_Y = -70f
    const val YELLOW_ROTATION = -25f
    const val YELLOW_TRANSLATION_X = -75f
    const val YELLOW_TRANSLATION_Y = -90f
    const val GREEN_TRANSLATION_Y = -170f

    //Home Fragment
    const val LANGUAGE_KEY = "language_key"
    const val LANGUAGE_IN = "in"
    const val LANGUAGE_EN = "en"

}
fun String.toBase64() = Base64.getEncoder().encodeToString(this.toByteArray())

fun ChipGroup.getSelectedChip(): String? {
    val selected = mutableListOf<String>()

    for (i in 0 until childCount) {
        val chip: Chip = getChildAt(i) as Chip

        if (chip.isChecked) {
            selected.add(chip.text.toString().lowercase().trim())
        }
    }

    return if (selected.isNotEmpty()) selected.joinToString(", ") else null
}
