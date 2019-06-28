package com.example.kotlinfirstapp.dagger.component

import com.example.kotlinfirstapp.dagger.module.ActivityModule
import com.example.kotlinfirstapp.dagger.scope.ActivityScope
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.ui.loginUser.LoginActivity
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserActivity
import com.example.kotlinfirstapp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent : ActivityCompnentExposes {

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(registerUserActivity: RegisterUserActivity)

    fun inject(splashActivity: SplashActivity)
}
