package com.example.kotlinfirstapp.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseActivity
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.ActivityComponent
import com.example.kotlinfirstapp.dagger.qualifiers.ForActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {


    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    @ForActivity
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.setView(this)

        if (savedInstanceState == null) {
            Log.d("marko", "saved state null, displaying fragment")
            presenter.displayFragment()
        }


    }

    override fun getPresenter(): BasePresenter {
        return this.presenter as BasePresenter
    }


    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    fun getSupportAction(): ActionBar? {
        return supportActionBar
    }


}


