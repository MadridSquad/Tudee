package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.presentation.screens.add_task.AddTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ AddTaskViewModel(get(),get()) }
}