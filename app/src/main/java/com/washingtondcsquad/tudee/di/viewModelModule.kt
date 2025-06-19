package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddTaskViewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.EditTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.time.LocalDate

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::HomeViewModel)
    viewModel { BottomSheetTaskViewModel(get()) }
    viewModel { (taskDate: LocalDate, onCancel: () -> Unit) ->
        AddTaskViewModel(
            tasksService = get(),
            categoryService = get(),
            taskDate = taskDate,
            onCancelAddTaskBottomSheet = onCancel
        )
    }

    viewModel { (taskId:Int , onCancel: () -> Unit) ->
        EditTaskViewModel(
            tasksService = get(),
            categoryService = get(),
            taskId = taskId,
            onCancelAddTaskBottomSheet = onCancel
        )
    }

}
