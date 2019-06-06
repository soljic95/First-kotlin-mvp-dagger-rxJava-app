package com.example.kotlinfirstapp.di.module

import com.example.kotlinfirstapp.adapter.MyViewPagerAdapter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsContract
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsPresenter
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsContract
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsPresenter
import com.example.kotlinfirstapp.viewPager.ViewPagerContract
import com.example.kotlinfirstapp.viewPager.ViewPagerPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FragmentModule {

    @Provides
    fun provideViewPagerPresenter(
        viewPagerAdapter: MyViewPagerAdapter,
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): ViewPagerContract.Presenter {
        return ViewPagerPresenter(viewPagerAdapter, coinDao, retrofit, router)
    }

    @Provides
    fun provideSearchCoinsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): SearchCoinsContract.Presenter {
        return SearchCoinsPresenter(retrofit, coinDao, router)
    }

    @Provides
    fun provideCoinDetailsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): CoinDetailsContract.Presenter {
        return CoinDetailsPresenter(retrofit, coinDao, router)
    }


    interface Exposes {

    }
}