package com.example.kotlinfirstapp.ui.loginUser

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class LoginPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) : BasePresenter(retrofit, coinDao, router),
    LoginContract.Presenter {


    private var loginView: LoginContract.View? = null


    override fun setLoginView(loginView: LoginContract.View) {
        this.loginView = loginView
        Log.d("marko", "presenter set")
    }

    override fun loginClicked(userName: String, password: String) {
        showProgressBar()
        addDisposable(
            getCoinDao().getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userRecived ->
                    Log.d("marko", "user recived : ${userRecived.userName}")
                    validateUserCredentials(userRecived, password)
                },
                    { errorMsg ->
                        Log.d("marko", "error occured ${errorMsg.localizedMessage}")
                        noSuchUserInDb()
                        hideProgressBar()
                    })
        )
    }

    override fun registerClicked() {
        Log.d("marko", "got here(presenter")
        getRouter().goToRegisterPage()
    }

    fun validateUserCredentials(user: User, password: String) {
        if (user.password == password) {
            getRouter().goToMainPage()
        } else {
            loginView?.onWrongPassword()
            hideProgressBar()
        }
    }

    fun showProgressBar() {
        loginView?.onDisplayProgressBar()
    }

    fun hideProgressBar() {
        loginView?.onHideProgressBar()
    }

    fun noSuchUserInDb() {
        loginView?.onNoUserInDb()
    }


}