package com.washingtondcsquad.tudee.di

import com.washingtondcsquad.tudee.data.localSource.TudeeDataBase
import org.koin.dsl.module


val dataBaseModule =
    module {
        single { TudeeDataBase.getInstance(context = get()).daoCategory() }
    }