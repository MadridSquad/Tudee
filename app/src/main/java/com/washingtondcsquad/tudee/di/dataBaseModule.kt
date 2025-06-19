package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.dataSources.CategoryLocalDataSource
import com.washingtondcsquad.tudee.data.dataSources.TaskLocalDataSource
import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.localSource.dataSourcesImplementations.CategoryLocalDataSourceImpl
import com.washingtondcsquad.tudee.data.localSource.dataSourcesImplementations.TaskLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        singleOf(::CategoryLocalDataSourceImpl) bind CategoryLocalDataSource::class
        single { TudeeDataBase.getInstance(context = get()).daoTask() }
        singleOf(::TaskLocalDataSourceImpl) bind TaskLocalDataSource::class
    }



