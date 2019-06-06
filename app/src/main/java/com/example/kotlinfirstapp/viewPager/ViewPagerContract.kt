package com.example.kotlinfirstapp.viewPager

import com.example.kotlinfirstapp.adapter.MyViewPagerAdapter

interface ViewPagerContract {

    interface View {
        fun onAdapterReady(adapter: MyViewPagerAdapter)

    }

    interface Presenter {

        fun setView(viewPagerView: View)
        fun init()

    }
}