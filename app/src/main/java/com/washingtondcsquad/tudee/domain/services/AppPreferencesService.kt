package com.washingtondcsquad.tudee.domain.services

import kotlinx.coroutines.flow.Flow

interface AppPreferencesService {
    fun hasOnboardingBeenShown(): Flow<Boolean>
    suspend fun setOnboardingShown()
    fun isDarkModeEnabled(): Flow<Boolean>
    suspend fun setDarkMode(isEnabled: Boolean) //TODO replace boolean by enum class
    //TODO choose suitable name for dark mode function set and get
}