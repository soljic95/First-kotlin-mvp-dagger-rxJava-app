package com.example.kotlinfirstapp.ui.registerUser

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.*

class RegisterUserPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) :
    BasePresenter(retrofit, coinDao, router),
    RegisterUserContract.Presenter {


    private lateinit var regView: RegisterUserContract.View

    private lateinit var user: User


    override fun registerUserClicked(userName: String, password: String) {
        showProgressBar()
        if (isDataValid(userName, password)) {
            user = User(userName, password)
            addDisposable(
                Completable.fromAction { getCoinDao().insertUser(user) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("marko", "user inserted")
                        logUser()
                    },
                        { errorMsg ->
                            Log.d("marko", "there was an error : ${errorMsg.localizedMessage}")
                            hideProgressBar()
                            displayErrorWhileInserting()
                        })
            )
        } else hideProgressBar()
    }

    override fun setRegisterView(registerView: RegisterUserContract.View) {
        this.regView = registerView
    }

    override fun showProgressBar() {
        regView.onShowProgressBar()
    }

    override fun hideProgressBar() {
        regView.onHideProgressBar()
    }

    private fun isDataValid(userName: String, password: String): Boolean {
        var isDataValid = true

        if (userName.length < 3) {
            isDataValid = false
            reportUsernameError()
        }

        if (password.length < 7) {
            isDataValid = false
            reportPasswordError()
        }

        return isDataValid
    }

    private fun reportUsernameError() {
        regView.onUserNameError()
    }

    private fun reportPasswordError() {
        regView.onPasswordError()
    }

    override fun displayErrorWhileInserting() {
        regView.onErrorWhileInserting()
    }

    private fun logUser() {
        user.isUserLoggedIn = true
        addDisposable(
            Completable.fromAction { getCoinDao().logUser(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("marko", "userUpdated")
                    getRouter().goToMainPage()
                },
                    { errorMsg -> Log.d("marko", "error happend ${errorMsg.localizedMessage}") })
        )
    }
}