package com.example.kotlinfirstapp.di.component

import com.example.kotlinfirstapp.viewPager.ViewPagerFragment
import com.example.kotlinfirstapp.di.module.FragmentModule
import com.example.kotlinfirstapp.di.scope.FragmentScope
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsContract
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsFragment
import com.example.kotlinfirstapp.ui.searchCoins.SearchCoinsFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ActivityComponent::class], modules = [FragmentModule::class])
interface FragmentComponent : FragmentExposesComponent {

    fun inject(viewPagerFragment: ViewPagerFragment)
    fun inject(searchCoinsFragment: SearchCoinsFragment)
    fun inject(coinDetailsFragment: CoinDetailsFragment)
}