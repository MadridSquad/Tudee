package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.add_task.AddTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.task_details.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.features.edit_task.EditTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesViewModel


val viewModelModule = module {
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::HomeViewModel)
    viewModel{ TasksViewModel(
        get(),
        get()
    ) }
    viewModel { BottomSheetTaskViewModel(get()) }
    viewModelOf(::AddTaskViewModel)

    viewModel { (taskId: Int, onCancel: () -> Unit, onActionResult: (success: Boolean, message: String) -> Unit) ->
        EditTaskViewModel(
            tasksService = get(),
            categoryService = get(),
        )
    }

}
