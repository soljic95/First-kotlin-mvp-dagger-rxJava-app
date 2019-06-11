package com.example.kotlinfirstapp.ui.splash

import android.os.Bundle
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseActivity
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.ActivityComponent
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {


    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.init()
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }
}
