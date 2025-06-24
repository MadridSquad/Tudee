package com.washingtondcsquad.tudee.domain.services


interface ImageSaverService {
    suspend fun saveImage(imageUrl: String, getFilePath: suspend (String) -> Unit)
    suspend fun deleteImage(imageUrl: String): Boolean
}