package com.washingtondcsquad.tudee.data.services

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.util.Locale

class AppPreferencesServiceImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferencesService {

    private val _currentLocale = MutableStateFlow(getCurrentAppLocale())
    val currentLocale: Flow<Locale> = _currentLocale.asStateFlow()

    override suspend fun getCurrentLocale(): Flow<Locale> = currentLocale.distinctUntilChanged()


    private fun getCurrentAppLocale(): Locale {
        val androidLocale = AppCompatDelegate.getApplicationLocales()[0]
            ?: Locale.getDefault()
        return Locale.Builder()
            .setLocale(androidLocale)
            .setLanguage(androidLocale.language)
            .build()
    }

    companion object PreferencesKeys {
        val HAS_ONBOARDING_BEEN_SHOWN = booleanPreferencesKey("has_onboarding_been_shown")
        val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    }

    override fun hasOnboardingBeenShown(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[HAS_ONBOARDING_BEEN_SHOWN] == true
        }
    }

    override suspend fun setOnboardingShown() {
        dataStore.edit { preferences ->
            preferences[HAS_ONBOARDING_BEEN_SHOWN] = true
        }
    }

    override fun isDarkModeEnabled(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[DARK_MODE_ENABLED] == true
        }
    }

    override suspend fun setDarkMode(isEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_ENABLED] = isEnabled
        }
    }
}