package com.washingtondcsquad.tudee.data.localSource.imageStorageManager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.washingtondcsquad.tudee.data.localSource.utils.toMD5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class SaveImageToInternalStorage(private val context: Context) {
    suspend fun saveImageToInternalStorage(
        imageUrl: String, getFilePath: suspend (String) -> Unit
    ) {
        val request = ImageRequest.Builder(context).data(imageUrl).build()

        val result = context.imageLoader.execute(request)
        if (result is SuccessResult) {
            val bitmap = (result.drawable as BitmapDrawable).bitmap
                ?: throw IllegalStateException("Failed to extract bitmap") //TODO remove any text to string file
            val fileName = imageUrl.toMD5() + ".png"

            val file = File(context.filesDir, fileName)
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { out ->
                    getFilePath(file.absolutePath)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                    out.flush()
                }
            }
        } else {
            throw IllegalStateException("Failed to load image")
        }
    }

    suspend fun saveImageToInternalStorage(
        drawableResId: Int, getFilePath: suspend (String) -> Unit
    ) {
        val request = ImageRequest.Builder(context).data(drawableResId).build()
        val result = context.imageLoader.execute(request)
        if (result is SuccessResult) {
            val bitmap = (result.drawable as? BitmapDrawable)?.bitmap
                ?: throw IllegalStateException("Failed to extract bitmap from drawable")
            val fileName = drawableResId.toString().toMD5() + ".png"
            val file = File(context.filesDir, fileName)
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { out ->
                    getFilePath(file.absolutePath)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                    out.flush()
                }
            }
        } else {
            throw IllegalStateException("Failed to load image from drawable")
        }
    }
}