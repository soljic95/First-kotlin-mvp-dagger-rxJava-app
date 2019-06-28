package com.example.kotlinfirstapp.ui.splash

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class SplashPresenter(retrofit: Retrofit, coinDao: CoinDao, router: Router) : BasePresenter(retrofit, coinDao, router),
    SplashContract.Presenter {


    private lateinit var view: SplashContract.View


    override fun setView(view: SplashContract.View) {
        this.view = view
    }

    override fun init() {
        addDisposable(
            getCoinDao().getLoggedUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.d("marko", "a user is logged in +${response.userName}")
                    getRouter().goToMainPage()
                },
                    { errorMsg ->
                        Log.d("marko", "an error occured ${errorMsg.localizedMessage}")
                        getRouter().goToLoginPage()
                    })
        )
    }
}