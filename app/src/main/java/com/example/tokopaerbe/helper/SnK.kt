package com.example.tokopaerbe.helper

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.tokopaerbe.R

class SnK {
    companion object {
        fun applyCustomTextColo(context: Context, fullText: String): SpannableString {
            val spannableString = SpannableString(fullText)

            val startGreen = fullText.indexOf("Syarat & Ketentuan")
            val endGreen = startGreen + "Syarat & Ketentuan".length

            val startBlue = fullText.indexOf("Kebijakan Privasi")
            val endBlue = startBlue + "Kebijakan Privasi".length

            val customGreenColor = ContextCompat.getColor(context, R.color.primaryColor)

            if (startGreen >= 0 && endGreen <= spannableString.length) {
                spannableString.setSpan(
                    ForegroundColorSpan(customGreenColor),
                    startGreen,
                    endGreen,
                    0
                )
            }

            // Pastikan start dan end tidak melebihi panjang teks
            if (startBlue >= 0 && endBlue <= spannableString.length) {
                spannableString.setSpan(
                    ForegroundColorSpan(customGreenColor),
                    startBlue,
                    endBlue,
                    0
                )
            }
            return spannableString
        }
    }
}