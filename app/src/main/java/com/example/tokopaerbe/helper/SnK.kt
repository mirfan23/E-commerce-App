package com.example.tokopaerbe.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.example.tokopaerbe.R

class SnK {
    companion object {
        fun applyCustomTextColor(context: Context, fullText: String): SpannableString {
            val spannableString = SpannableStringBuilder(fullText)

            val startGreen = fullText.indexOf("Syarat & Ketentuan")
            val endGreen = startGreen + "Syarat & Ketentuan".length

            val startBlue = fullText.indexOf("Kebijakan Privasi")
            val endBlue = startBlue + "Kebijakan Privasi".length

            val customGreenColor = ContextCompat.getColor(context, R.color.primaryColor)

            val greenClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    // Handle click for "Syarat & Ketentuan"
                    openUrl(context, "https://www.youtube.com")
                }
            }
            val blueClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    // Handle click for "Kebijakan Privasi"
                    openUrl(context, "https://www.bing.com")
                }
            }
            spannableString.setSpan(greenClickableSpan, startGreen, endGreen, 0)
            spannableString.setSpan(blueClickableSpan, startBlue, endBlue, 0)

            spannableString.setSpan(
                ForegroundColorSpan(customGreenColor),
                startGreen,
                endGreen,
                0
            )
            spannableString.setSpan(
                ForegroundColorSpan(customGreenColor),
                startBlue,
                endBlue,
                0
            )

            return SpannableString(spannableString)

        }
        private fun openUrl(context: Context, url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)}
    }


}
