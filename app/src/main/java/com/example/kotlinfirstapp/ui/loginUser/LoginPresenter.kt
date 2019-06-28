package com.example.kotlinfirstapp.ui.loginUser

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import io.reactivex.Completable
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

    private fun validateUserCredentials(user: User, password: String) {
        if (user.password == password) {
            user.isUserLoggedIn = true
            addDisposable(
                Completable.fromAction { getCoinDao().logUser(user) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("marko", "userLogged as logged in")
                        getRouter().goToMainPage()
                    },
                        { errorMsg -> Log.d("marko", "error ${errorMsg.localizedMessage}") })
            )
        } else {
            loginView?.onWrongPassword()
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        loginView?.onDisplayProgressBar()
    }

    private fun hideProgressBar() {
        loginView?.onHideProgressBar()
    }

    private fun noSuchUserInDb() {
        loginView?.onNoUserInDb()
    }


}