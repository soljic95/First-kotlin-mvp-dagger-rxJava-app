package com.example.kotlinfirstapp.viewPager

interface ViewPagerContract {

    interface View {
        fun onAdapterReady(adapter: MyViewPagerAdapter)

        fun displayProgressDialog()


    }

    interface Presenter {

        fun setView(viewPagerView: View)

        fun init()


        fun logOutClicked()

    }
}