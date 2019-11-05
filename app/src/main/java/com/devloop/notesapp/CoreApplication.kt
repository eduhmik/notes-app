package com.devloop.notesapp

import android.app.Application
import com.devloop.notesapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(appModules)
        }
    }
}