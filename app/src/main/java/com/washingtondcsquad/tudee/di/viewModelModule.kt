package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.screen.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.AddTaskViewModel
import com.washingtondcsquad.tudee.presentation.screens.add_task.EditTaskViewModel
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.time.LocalDate
import com.washingtondcsquad.tudee.presentation.categories.CategoriesViewModel


val viewModelModule = module {
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::HomeViewModel)
    viewModel{ TasksViewModel(
        get(),
        get()
    ) }
    viewModel { BottomSheetTaskViewModel(get()) }
    viewModel { (taskDate: LocalDate, onCancel: () -> Unit, onActionResult: (success: Boolean, message: String) -> Unit) ->
        AddTaskViewModel(
            tasksService = get(),
            categoryService = get(),
            taskDate = taskDate,
            onCancelAddTaskBottomSheet = onCancel,
            onActionResult = onActionResult
        )
    }

    viewModel { (taskId:Int , onCancel: () -> Unit , onActionResult: (success: Boolean, message: String) -> Unit) ->
        EditTaskViewModel(
            tasksService = get(),
            categoryService = get(),
            taskId = taskId,
            onCancelAddTaskBottomSheet = onCancel,
            onActionResult = onActionResult
        )
    }

}
