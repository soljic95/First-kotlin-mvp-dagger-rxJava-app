package com.example.kotlinfirstapp.ui.myCoins


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.model.Data
import kotlinx.android.synthetic.main.fragment_my_coins.*
import javax.inject.Inject


class MyCoinsFragment : BaseFragment(), MyCoinsContract.View {


    @Inject
    lateinit var presenter: MyCoinsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_my_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setUpView(this)
        presenter.init()

    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCoinsReady(listOfCoins: ArrayList<Data>) {
        for (coin in listOfCoins) {
            coins.text = coins.text.toString() + coin.name
        }
        onHideProgressBar()
    }

    override fun onHideProgressBar() {
        myCoinsProgressBar.visibility = View.INVISIBLE

    }

    override fun onDisplayProgressBar() {
        myCoinsProgressBar.visibility = View.VISIBLE
    }


}
