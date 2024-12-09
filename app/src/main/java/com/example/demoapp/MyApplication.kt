package com.example.demoapp

import android.app.Application
import com.example.demoapp.di.RetroComponent
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


    }
}
