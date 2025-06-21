package com.washingtondcsquad.tudee.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.DeleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.SaveImageToInternalStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

val dataBaseModule = module {
    single { TudeeDataBase.getInstance(context = get()).daoCategory() }
    singleOf(::SaveImageToInternalStorage)
    singleOf(::DeleteImageFromInternalStorage)
    single { TudeeDataBase.getInstance(context = get()).daoTask() }
    single { androidApplication().applicationContext.appPreferencesDataStore }

    singleOf(::SaveImageToInternalStorage)
    singleOf(::DeleteImageFromInternalStorage)
}