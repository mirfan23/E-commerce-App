package com.example.tokopaerbe.helper

import com.example.tokopaerbe.R

object ValidationHelper {

    fun String?.validateEmail(): Boolean {
        val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val isValid = this?.let { emailPattern.toRegex().matches(it) }
        return isValid == true
    }

     fun String?.validatePassword(): Boolean {
        val isValid = this?.let {it.length >= 8}
        /**
         * akan digunakan kembali
         */
//        if (password.none { it.isUpperCase() }) {
//            showError("Password setidaknya terdapat satu huruf kapital")
//            return false
//        }
//        if (password.none { !it.isLetterOrDigit() }) {
//            showError("Password harus mengandung setidaknya satu karakter khusus")
//            return false
//        }
         return isValid == true
    }

    fun String.validateRequired(): Boolean = this.isNotEmpty() == true
}