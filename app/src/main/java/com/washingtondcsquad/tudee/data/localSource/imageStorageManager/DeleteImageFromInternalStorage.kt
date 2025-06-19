package com.washingtondcsquad.tudee.data.localSource.imageStorageManager

import android.content.Context
import com.washingtondcsquad.tudee.data.localSource.utils.toMD5
import java.io.File

class DeleteImageFromInternalStorage(private val context: Context) {
    fun deleteImageFromInternalStorage(imageUrl: String): Boolean {
        val fileName = imageUrl.toMD5() + ".png"
        val file = File(context.filesDir, fileName)
        return if (file.exists()) {
            file.delete()
        } else {
            throw IllegalStateException("Failed to delete image")
        }
    }
}