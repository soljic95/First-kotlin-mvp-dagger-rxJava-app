package com.example.kotlinfirstapp.dagger.component

import com.example.kotlinfirstapp.viewPager.ViewPagerFragment
import com.example.kotlinfirstapp.dagger.module.FragmentModule
import com.example.kotlinfirstapp.dagger.scope.FragmentScope
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsFragment
import com.example.kotlinfirstapp.ui.myCoins.MyCoinsFragment
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class])
interface FragmentComponent : FragmentExposesComponent {

    fun inject(viewPagerFragment: ViewPagerFragment)
    fun inject(searchCoinsFragment: SearchCoinsFragment)
    fun inject(coinDetailsFragment: CoinDetailsFragment)
    fun inject(myCoinsFragment: MyCoinsFragment)
}