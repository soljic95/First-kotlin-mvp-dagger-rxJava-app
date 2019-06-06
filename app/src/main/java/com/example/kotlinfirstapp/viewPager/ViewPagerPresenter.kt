package com.example.kotlinfirstapp.viewPager

import com.example.kotlinfirstapp.adapter.MyViewPagerAdapter
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsFragment
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsFragment
import retrofit2.Retrofit

class ViewPagerPresenter(
    var viewPagerAdapter: MyViewPagerAdapter,
    coinDao: CoinDao,
    retrofit: Retrofit,
    router: Router
) :
    BasePresenter(retrofit, coinDao, router), ViewPagerContract.Presenter {


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

}