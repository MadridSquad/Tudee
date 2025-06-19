package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.services.appPreferencesDataStore
import org.koin.android.ext.koin.androidApplication

import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        single { TudeeDataBase.getInstance(context = get()).daoTask() }
        single { androidApplication().applicationContext.appPreferencesDataStore }

    }