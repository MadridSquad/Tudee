package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.services.AppPreferencesServiceImpl
import com.washingtondcsquad.tudee.data.services.CategoriesServiceImpl
import com.washingtondcsquad.tudee.data.services.TasksServiceImpl
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.provider.StringProviderImp
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::TasksServiceImpl) bind TasksService::class
    singleOf(::CategoriesServiceImpl) bind CategoriesService::class
    singleOf(::AppPreferencesServiceImpl) bind AppPreferencesService::class
    singleOf(::StringProviderImp) bind StringProvider::class

}