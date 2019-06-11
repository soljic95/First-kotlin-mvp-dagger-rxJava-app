package com.example.kotlinfirstapp.ui.loginUser

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseActivity
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.ActivityComponent
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.setLoginView(this)
        ButterKnife.bind(this)
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @OnClick(R.id.tvRegister)
    fun registerClicked() {
        Log.d("marko", "register clicked")
        presenter.registerClicked()
    }

    @OnClick(R.id.btnLogin)
    fun buttonClicked() {
        presenter.loginClicked(etUserName.text.toString(), etPassword.text.toString())
    }

    override fun onWrongPassword() {
        Log.d("marko", "password IS WRONG")
        etPassword.error = getString(R.string.password_not_correct)
    }

    override fun onNoUserInDb() {
        Log.d("marko", "user does not exist")
        etUserName.error = getString(R.string.user_doesnt_exist)
        Toast.makeText(baseContext, "User doesnt exist", Toast.LENGTH_SHORT).show()
    }

    override fun onDisplayProgressBar() {
        logInprogressBar.visibility = View.VISIBLE
    }

    override fun onHideProgressBar() {
        logInprogressBar.visibility = View.INVISIBLE
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

}
