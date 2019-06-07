package com.example.kotlinfirstapp.ui.coinDetails


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast

import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.model.Data
import kotlinx.android.synthetic.main.fragment_coin_details.*
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
        init()

    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.coin_details_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> presenter.goBackInFragment()
            R.id.saveCoinMenuItem -> Log.d("marko", "clicked save coin")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        val actionBar = (activity as MainActivity).getSupportAction()
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        val coin: Data? = arguments?.getParcelable("coin") //todo string placeholder stavi tu
        tvName.text = "Coin name: ${coin?.name}"
        tvAcronym.text = "Coin acronym: ${coin?.acronym}"
        tvNetwork.text = coin?.network
        tvSymbol.text = coin?.symbol_htmlcode
        tvUrl.text = coin?.url
        tvMiningDifficulty.text = coin?.mining_difficulty
        tvUnconfirmed.text = coin?.unconfirmed_txs.toString()
        tvBlocks.text = coin?.blocks.toString()
        tvPrice.text = "Coin price: ${coin?.price} ${coin?.price_base}"
        tvPriceUpdateTime.text = coin?.price_update_time.toString()
        tvHashRate.text = coin?.hashrate


    }


}
