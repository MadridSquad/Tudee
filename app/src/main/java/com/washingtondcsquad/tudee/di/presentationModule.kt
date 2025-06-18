package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingViewModel() }
    viewModel{ AddTaskViewModel(get(),get()) }
}