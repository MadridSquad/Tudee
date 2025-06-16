package com.washingtondcsquad.tudee.domain.services

interface LocaleService {
    suspend fun switchAppTheme(isDarkTheme:Boolean)
}