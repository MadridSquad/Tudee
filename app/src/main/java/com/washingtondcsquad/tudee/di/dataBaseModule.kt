package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.CategoryLocalDataSourceImpl
import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.services.CategoryLocalDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        singleOf(::CategoryLocalDataSourceImpl) bind CategoryLocalDataSource::class
    }