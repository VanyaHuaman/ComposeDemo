package com.example.composedemo

import android.app.Application
import com.example.composedemo.di.Modules.appModule
import com.example.composedemo.di.Modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComposeDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ComposeDemoApp)
            modules(appModule, viewModelModule)
        }
    }
}