package com.example.kotlinfirstapp.di.component

import com.example.kotlinfirstapp.di.module.ActivityModule
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.splash.SplashActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
}