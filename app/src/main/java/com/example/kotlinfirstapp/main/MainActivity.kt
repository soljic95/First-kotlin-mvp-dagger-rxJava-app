package com.example.kotlinfirstapp.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.di.DaggerActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerActivityComponent.create().inject(this)

        presenter.setMainView(this)
        btnChangeText.setOnClickListener {
            presenter.btnClicked()
            Log.d("marko", "btn clicked")
        }
    }

    override fun onNameGot(coinName: String) {
        tvHelloWorld.text = coinName
    }

    override fun onShowProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }


}


