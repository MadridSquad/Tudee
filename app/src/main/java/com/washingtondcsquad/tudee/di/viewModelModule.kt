package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { OnboardingViewModel() }
    viewModelOf(::HomeViewModel)
    viewModel { BottomSheetTaskViewModel(get()) }
    viewModel { TasksViewModel(get(), get()) }
}