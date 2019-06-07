package com.example.kotlinfirstapp.base

import android.app.Application
import com.example.kotlinfirstapp.dagger.component.ApplicationComponent
import com.example.kotlinfirstapp.dagger.component.DaggerApplicationComponent
import com.example.kotlinfirstapp.dagger.module.ApplicationModule

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