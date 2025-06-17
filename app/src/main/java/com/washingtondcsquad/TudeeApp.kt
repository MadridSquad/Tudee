package com.washingtondcsquad

import android.app.Application
import com.washingtondcsquad.tudee.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TudeeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TudeeApp)
            modules(appModule)
        }
    }
}