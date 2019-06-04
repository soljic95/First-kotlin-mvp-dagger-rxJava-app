package com.example.kotlinfirstapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstapp.di.component.ActivityComponent
import com.example.kotlinfirstapp.di.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.create()
        inject(activityComponent)
    }

    abstract fun inject(activityComponent: ActivityComponent)

}