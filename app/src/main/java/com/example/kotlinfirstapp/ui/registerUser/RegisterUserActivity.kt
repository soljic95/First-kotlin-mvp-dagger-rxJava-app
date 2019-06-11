package com.example.kotlinfirstapp.ui.registerUser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseActivity
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.ActivityComponent
import kotlinx.android.synthetic.main.activity_register_user.*
import javax.inject.Inject

class RegisterUserActivity : BaseActivity(),
    RegisterUserContract.View {


    @Inject
    lateinit var presenter: RegisterUserContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        ButterKnife.bind(this)
        presenter.setRegisterView(this)
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @OnClick(R.id.btnCreateAccount)
    fun createAccountClicked() {
        presenter.registerUserClicked(
            etRegUsername.text.toString(),
            etRegPassword.text.toString()
        )
    }

    override fun onShowProgressBar() {
        regProgressBar.visibility = View.VISIBLE
    }

    override fun onHideProgressBar() {
        regProgressBar.visibility = View.INVISIBLE
    }

    override fun onErrorWhileInserting() {
        Toast.makeText(baseContext, getString(R.string.user_duplicate_toast), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onUserNameError() {
        etRegUsername.error = getString(R.string.username_error)
    }

    override fun onPasswordError() {
        etRegPassword.error = getString(R.string.password_error)

    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }
}