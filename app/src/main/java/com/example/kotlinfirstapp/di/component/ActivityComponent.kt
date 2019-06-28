package com.example.kotlinfirstapp.di.component

import com.example.kotlinfirstapp.di.module.ActivityModule
import com.example.kotlinfirstapp.di.scope.ActivityScope
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.ui.loginUser.LoginActivity
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserActivity
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent : ActivitiyCompnentExposes {

    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(registerUserActivity: RegisterUserActivity)
}