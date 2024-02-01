package com.example.tokopaerbe.helper

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object MediaHelper {

    fun File.toRequestBody() = this.asRequestBody("multipart/form-data".toMediaTypeOrNull())

    fun convertFileFromContentUri(context: Context, uri: Uri): File? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.use { input ->
                val file = createTemporaryFile(context)
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
                return file
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }

    private fun createTemporaryFile(context: Context): File {
        val fileName = "temp_file"
        val directory = context.cacheDir
        return File.createTempFile(fileName, null, directory)
    }
}