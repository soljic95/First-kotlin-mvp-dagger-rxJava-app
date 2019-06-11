package com.example.kotlinfirstapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstapp.dagger.component.ActivityComponent
import com.example.kotlinfirstapp.dagger.component.DaggerActivityComponent
import com.example.kotlinfirstapp.dagger.module.ActivityModule

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
            .activityModule(ActivityModule(this, supportFragmentManager))
            .applicationComponent((application as BaseApplication).getApplicationComponent())
            .build()
    }

    fun getComponent(): ActivityComponent {
        return this.activityComponent
    }

    protected abstract fun getPresenter(): BasePresenter

    override fun onPause() {
        getPresenter().deactivate()
        super.onPause()
    }


    override fun onResume() {
        getPresenter().activate()
        super.onResume()
    }
}