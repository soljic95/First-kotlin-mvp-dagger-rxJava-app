package com.example.kotlinfirstapp.base

import android.app.Application
import com.example.kotlinfirstapp.di.component.ApplicationComponent
import com.example.kotlinfirstapp.di.component.DaggerApplicationComponent
import com.example.kotlinfirstapp.di.module.ApplicationModule

class BaseApplication() : Application() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return applicationComponent
    }
}