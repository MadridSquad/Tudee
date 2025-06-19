package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddTaskViewModel
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.time.LocalDate

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::HomeViewModel)
    viewModel{ TasksViewModel(
        get(),
        get()
    ) }
    viewModel { BottomSheetTaskViewModel(get()) }
    viewModel {
        AddTaskViewModel(
            get(),
            get(),
            initialDate = LocalDate.now(),
        )
    }

}
