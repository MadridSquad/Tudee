package com.washingtondcsquad.tudee.presentation.features.onBoarding

import android.util.Log
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel

class OnboardingViewModel(
    private val appPreferencesService: AppPreferencesService
) : BaseViewModel<OnboardingUiState>(
    initialValue = OnboardingUiState(
        currentPage = 0,
        pages = listOf(
            OnboardingPage(
                imageResId = R.drawable.onboarding_1,
                titleResId = R.string.onboarding_title_1,
                descriptionResId = R.string.onboarding_description_1
            ),
            OnboardingPage(
                imageResId = R.drawable.onboarding_2,
                titleResId = R.string.onboarding_title_2,
                descriptionResId = R.string.onboarding_description_2
            ),
            OnboardingPage(
                imageResId = R.drawable.onboarding_3,
                titleResId = R.string.onboarding_title_3,
                descriptionResId = R.string.onboarding_description_3
            ),
        ),
        isLastPage = false
    )
) {
    private val lastIndex = state.value.pages.lastIndex

    fun onPageChanged(index: Int) {
        updateState {
            copy(
                currentPage = index,
                isLastPage = index == lastIndex
            )
        }
    }

    fun onNext() {
        val next = (state.value.currentPage + 1).coerceAtMost(lastIndex)
        onPageChanged(next)
    }
    fun onboardingFinished() {
        tryToExecute(
            request = { appPreferencesService.setOnboardingShown() },
            onSuccess = { },
            onError = {
                Log.e("OnboardingViewModel", "onboardingFinished: ", it)
            }
        )

    }
}