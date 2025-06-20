package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.DeleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.SaveImageToInternalStorage
import org.koin.core.module.dsl.singleOf
import com.washingtondcsquad.tudee.data.services.appPreferencesDataStore
import org.koin.android.ext.koin.androidApplication

import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        singleOf(::SaveImageToInternalStorage)
        singleOf(::DeleteImageFromInternalStorage)
        single { TudeeDataBase.getInstance(context = get()).daoTask() }
        single { androidApplication().applicationContext.appPreferencesDataStore }

        singleOf(::SaveImageToInternalStorage)
        singleOf(::DeleteImageFromInternalStorage)
    }