package com.example.kotlinfirstapp.dagger.module

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfirstapp.dagger.scope.FragmentScope
import com.example.kotlinfirstapp.data.CoinDao
import com.example.kotlinfirstapp.router.Router
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsContract
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsPresenter
import com.example.kotlinfirstapp.ui.myCoins.CoinsRecyclerAdapter
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsContract
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsPresenter
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsContract
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsPresenter
import com.example.kotlinfirstapp.viewPager.MyViewPagerAdapter
import com.example.kotlinfirstapp.viewPager.ViewPagerContract
import com.example.kotlinfirstapp.viewPager.ViewPagerPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FragmentModule(private val fragmentManager: FragmentManager) {

    @Provides
    @FragmentScope
    fun provideFragmentManger(): FragmentManager {
        return fragmentManager
    }

    @Provides
    @FragmentScope
    fun provideViewPagerAdapter(fragmentManager: FragmentManager): MyViewPagerAdapter {
        return MyViewPagerAdapter(fragmentManager)
    }

    @Provides
    @FragmentScope
    fun provideViewPagerPresenter(
        viewPagerAdapter: MyViewPagerAdapter,
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): ViewPagerContract.Presenter {
        return ViewPagerPresenter(viewPagerAdapter, coinDao, retrofit, router)
    }

    @Provides
    @FragmentScope
    fun provideSearchCoinsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): SearchCoinsContract.Presenter {
        return SearchCoinsPresenter(retrofit, coinDao, router)
    }


    @Provides
    @FragmentScope
    fun provideCoinDetailsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): CoinDetailsContract.Presenter {
        return CoinDetailsPresenter(retrofit, coinDao, router)
    }

    @Provides
    @FragmentScope
    fun provideMyCoinsPresenter(
        retrofit: Retrofit,
        coinDao: CoinDao,
        router: Router
    ): MyCoinsContract.Presenter {
        return MyCoinsPresenter(retrofit, coinDao, router)
    }

    @Provides
    @FragmentScope
    fun provideCoinsRecyclerAdapter(inflater: LayoutInflater): CoinsRecyclerAdapter {
        return CoinsRecyclerAdapter(inflater)
    }

    @Provides
    @FragmentScope
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }


}