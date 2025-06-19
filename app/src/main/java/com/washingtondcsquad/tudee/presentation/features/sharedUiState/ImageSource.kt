package com.washingtondcsquad.tudee.presentation.features.sharedUiState

sealed interface ImageSource {
    data class Path(val value: String) : ImageSource
    data class Drawable(val id: Int) : ImageSource
}