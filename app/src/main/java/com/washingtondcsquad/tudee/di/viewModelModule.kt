package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.features.add_task.AddTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesViewModel
import com.washingtondcsquad.tudee.presentation.features.category_detail.CategoryDetailsScreenViewModel
import com.washingtondcsquad.tudee.presentation.features.edit_task.EditTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.home.HomeViewModel
import com.washingtondcsquad.tudee.presentation.features.onBoarding.OnboardingViewModel
import com.washingtondcsquad.tudee.presentation.features.task_details.BottomSheetTaskViewModel
import com.washingtondcsquad.tudee.presentation.features.tasksScreen.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


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



    viewModel {
        EditTaskViewModel(
            tasksService = get(),
            categoryService = get(),
            stringProvider = get(),
        )
    }
    viewModel { CategoryDetailsScreenViewModel(
        categoryService = get()
    ) }

}
