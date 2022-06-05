package com.samirmaciel.techquiz

import android.app.Application

import com.samirmaciel.techquiz.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApplication)
            modules(mainModule)
        }


    }
}