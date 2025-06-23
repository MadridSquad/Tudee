package com.washingtondcsquad.tudee.domain.customException

sealed class TudeeException : Exception() {

    sealed class LocalStorageException : TudeeException() {
        object AccessDenied : LocalStorageException()
        object FileNotFound : LocalStorageException()
        object DeletionFailed : LocalStorageException()
        object InsertionFailed : LocalStorageException()
        object UpdateFailed : LocalStorageException()
        data class UnKnown(val exception: Exception) : LocalStorageException()
    }
}