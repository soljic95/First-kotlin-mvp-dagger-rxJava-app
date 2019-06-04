package com.example.kotlinfirstapp.splash

import android.os.Bundle
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseActivity
import com.example.kotlinfirstapp.di.component.ActivityComponent
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.setSplashView(this)
        presenter.chooseScreen()
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

}
