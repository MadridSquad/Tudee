package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.washingtondcsquad.tudee.data.localSource.ImageStorageManager


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
        singleOf(::ImageStorageManager)
    }