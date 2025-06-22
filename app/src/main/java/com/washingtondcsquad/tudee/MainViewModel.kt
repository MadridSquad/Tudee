package com.washingtondcsquad.tudee

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val appPreferencesService: AppPreferencesService
) : BaseViewModel<MainState>(MainState()) {

    init {
        getIsDarkTheme()
        getOnBoardingState()
    }

    private fun getIsDarkTheme() {
        tryToExecute(
            request = {
                appPreferencesService.isDarkModeEnabled()
            },
            onSuccess = ::onDarkThemeEnabled,
            onError = {}
        )
    }

    private fun onDarkThemeEnabled(isDarkThemeEnabled: Flow<Boolean>) = viewModelScope.launch {
        isDarkThemeEnabled.collectLatest {
            updateState {
                copy(isDarkTheme = it)
            }
        }
    }

    private fun getOnBoardingState() {
        tryToExecute(
            request = {
                appPreferencesService.hasOnboardingBeenShown()
            },
            onSuccess = ::onOnBoardingState,
            onError = {}
        )
    }

    private fun onOnBoardingState(hasOnboardingBeenShown: Flow<Boolean>) = viewModelScope.launch {
        hasOnboardingBeenShown.collectLatest {
            updateState {
                copy(hasOnBoardingShown = it)
            }
        }
    }
}