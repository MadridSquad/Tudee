package com.washingtondcsquad.tudee.domain.services

import kotlinx.coroutines.flow.Flow

interface AppPreferencesService {
    fun hasOnboardingBeenShown(): Flow<Boolean>
    suspend fun setOnboardingShown()
    fun isDarkModeEnabled(): Flow<Boolean>
    suspend fun setDarkModeEnabled(isEnabled: Boolean)

}