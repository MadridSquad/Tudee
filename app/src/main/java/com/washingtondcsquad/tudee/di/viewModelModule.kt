package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import com.washingtondcsquad.tudee.data.services.TasksServiceImpl
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { OnboardingViewModel() }
    viewModelOf(::HomeViewModel)
    viewModel { BottomSheetTaskViewModel(get()) }
}