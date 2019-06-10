package com.example.kotlinfirstapp.dagger.module

import androidx.fragment.app.FragmentManager
import com.example.kotlinfirstapp.dagger.qualifiers.ForFragment
import com.example.kotlinfirstapp.dagger.scope.ActivityScope
import com.example.kotlinfirstapp.dagger.scope.FragmentScope
import com.example.kotlinfirstapp.viewPager.MyViewPagerAdapter
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsContract
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsPresenter
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsContract
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsPresenter
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsContract
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsPresenter
import com.example.kotlinfirstapp.viewPager.ViewPagerContract
import com.example.kotlinfirstapp.viewPager.ViewPagerPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FragmentModule(private val fragmentManager: FragmentManager) {

    @Provides
    fun provideFragmentManger(): FragmentManager {
        return fragmentManager
    }

    @Provides
    @FragmentScope
    fun provideViewPagerAdapter(fragmentManager: FragmentManager): MyViewPagerAdapter {
        return MyViewPagerAdapter(fragmentManager)
    }

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

    @Provides
    fun provideMyCoinsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): MyCoinsContract.Presenter {
        return MyCoinsPresenter(retrofit, coinDao, router)
    }


}