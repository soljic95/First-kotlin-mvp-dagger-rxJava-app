package com.example.kotlinfirstapp.ui.myCoins

import android.os.Bundle
import com.example.kotlinfirstapp.model.Data

interface MyCoinsContract {

    interface View {
        fun onCoinsReady(listOfCoins: ArrayList<Data>)

        fun onDisplayProgressBar()

        fun onHideProgressBar()


    }

    interface Presenter {
        fun setUpView(view: View)

        fun init()

        fun hideProgressBar()

        fun onRecyclerItemClicked(bundle: Bundle)

    }
}