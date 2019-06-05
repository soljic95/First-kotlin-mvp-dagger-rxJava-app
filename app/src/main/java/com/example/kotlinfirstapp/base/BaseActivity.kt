package com.example.kotlinfirstapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstapp.di.component.ActivityComponent
import com.example.kotlinfirstapp.di.component.DaggerActivityComponent
import com.example.kotlinfirstapp.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        inject(activityComponent)
    }

    abstract fun inject(activityComponent: ActivityComponent)

    private fun init() {
        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as BaseApplication).getApplicationComponent())
            .build()
    }

}