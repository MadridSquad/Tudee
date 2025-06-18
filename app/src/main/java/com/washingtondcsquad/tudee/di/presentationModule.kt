package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.services.TasksServiceImpl
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.features.taskdetails.BottomSheetTaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<TasksService> { TasksServiceImpl() }

    viewModel { BottomSheetTaskViewModel(get()) }
}