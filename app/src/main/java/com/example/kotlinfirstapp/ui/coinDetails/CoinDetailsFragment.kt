package com.example.kotlinfirstapp.ui.coinDetails


import android.os.Bundle
import android.view.*

import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.main.MainActivity
import javax.inject.Inject

class CoinDetailsFragment : BaseFragment(), CoinDetailsContract.View {

    @Inject
    lateinit var presenter: CoinDetailsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setActionBar()

    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter.goBackInFragment()
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        var actionBar = (activity as MainActivity).getSupportAction()
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }


}
