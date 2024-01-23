package com.example.tokopaerbe.helper

import android.text.Editable
import android.text.TextWatcher

class TextWatcherConfigure (
    private val characterLimit: Int,
    private val onTextChange: (String) -> Unit,
) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        if (charSequence.length >= characterLimit) {
            onTextChange.invoke((charSequence.toString()))
        }
    }
    override fun afterTextChanged(s: Editable?) {}
}
