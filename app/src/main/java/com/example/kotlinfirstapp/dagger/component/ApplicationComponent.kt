package com.example.kotlinfirstapp.dagger.component

import com.example.kotlinfirstapp.dagger.module.ApplicationModule
import com.example.kotlinfirstapp.dagger.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : ApplicationComponentExposes {
}