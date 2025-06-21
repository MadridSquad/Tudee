package com.washingtondcsquad.tudee.presentation.features.onBoarding

data class OnboardingUiState(
    val currentPage: Int = 0,
    val pages: List<OnboardingPage> = emptyList(),
    val isLastPage: Boolean = false
)

data class OnboardingPage(
    val imageResId: Int,
    val titleResId: Int,
    val descriptionResId: Int
)