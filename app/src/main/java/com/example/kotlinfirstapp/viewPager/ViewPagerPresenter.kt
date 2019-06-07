package com.example.kotlinfirstapp.viewPager

import android.util.Log
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.model.User
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsFragment
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class ViewPagerPresenter(
    var viewPagerAdapter: MyViewPagerAdapter,
    coinDao: CoinDao,
    retrofit: Retrofit,
    router: Router
) :
    BasePresenter(retrofit, coinDao, router), ViewPagerContract.Presenter {

    override fun logOutClicked() {
        viewPagerView.displayProgressDialog()
        addDisposable(
            getCoinDao().getLoggedUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user -> logOutUser(user) },
                    { errorMsg -> Log.d("marko", "error ${errorMsg.localizedMessage}") })
        )
    }


    private lateinit var viewPagerView: ViewPagerContract.View


    override fun setView(viewPagerView: ViewPagerContract.View) {
        this.viewPagerView = viewPagerView

    }

    override fun init() {
        viewPagerAdapter.clear()
        viewPagerAdapter.addFragment(MyCoinsFragment(), "My coins")
        viewPagerAdapter.addFragment(SearchCoinsFragment(), "Search coins")
        viewPagerView.onAdapterReady(viewPagerAdapter)
    }


    private fun logOutUser(user: User) {
        user.isUserLoggedIn = false
        addDisposable(Completable.fromAction { getCoinDao().logUser(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("marko", "user logged as logged out")
                getRouter().goToLoginPage()
            },
                { msg -> Log.d("marko", "error ${msg.localizedMessage}") })
        )
    }

}