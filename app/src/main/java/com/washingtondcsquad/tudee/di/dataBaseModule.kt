package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.DeleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.SaveImageToInternalStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        singleOf(::SaveImageToInternalStorage)
        singleOf(::DeleteImageFromInternalStorage)
    }