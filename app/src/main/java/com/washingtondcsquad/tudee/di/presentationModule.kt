package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingViewModel() }
}