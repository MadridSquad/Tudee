package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource

interface ImageSaverService {
    suspend fun saveImage(imageUrl: ImageSource, getFilePath: suspend (String) -> Unit)
    suspend fun deleteImage(imageUrl: ImageSource): Boolean
}