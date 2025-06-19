package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.services.CategoriesServiceImpl
import com.washingtondcsquad.tudee.data.services.FakeTasksService
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {
    singleOf<TasksService>(::FakeTasksService)
    singleOf(::CategoriesServiceImpl) bind CategoriesService::class
}