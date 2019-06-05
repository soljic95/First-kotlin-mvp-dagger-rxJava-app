package com.example.kotlinfirstapp.di.component

import com.example.kotlinfirstapp.di.module.ApplicationModule
import com.example.kotlinfirstapp.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : ApplicationComponentExposes {
}