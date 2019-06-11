package com.example.kotlinfirstapp.ui.coinDetails


import android.os.Bundle
import android.view.*

import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.model.Data
import kotlinx.android.synthetic.main.fragment_coin_details.*
import javax.inject.Inject

class CoinDetailsFragment : BaseFragment(), CoinDetailsContract.View {


    @Inject
    lateinit var presenter: CoinDetailsContract.Presenter

    var coin: Data? = null

    var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_coin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        init()
        setHasOptionsMenu(true)
        setActionBar()

    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        this.menu = menu
        inflater?.inflate(R.menu.coin_details_menu, menu)
        presenter.checkCoin(coin)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCoinPresent() {
        menu?.findItem(R.id.saveCoinMenuItem)?.isChecked = true
    }

    override fun onCoinNotPresent() {
        menu?.findItem(R.id.saveCoinMenuItem)?.isChecked = false

    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> presenter.goBackInFragment()
            R.id.saveCoinMenuItem -> presenter.saveCoinClicked(coin!!.name, item.isChecked)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBar() {
        val actionBar = (activity as MainActivity).getSupportAction()
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        coin = arguments?.getParcelable(getString(R.string.coin_key))
        tvName.text = String.format(resources.getString(R.string.coin_name), coin?.name)
        tvAcronym.text = String.format(resources.getString(R.string.coin_acronym), coin?.acronym)
        tvNetwork.text = coin?.network
        tvSymbol.text = coin?.symbol_htmlcode
        tvUrl.text = coin?.url
        tvMiningDifficulty.text = coin?.mining_difficulty
        tvUnconfirmed.text = coin?.unconfirmed_txs.toString()
        tvBlocks.text = coin?.blocks.toString()
        tvPrice.text = String.format(resources.getString(R.string.coin_price), coin?.price, coin?.price_base)
        tvPriceUpdateTime.text = coin?.price_update_time.toString()
        tvHashRate.text = coin?.hashrate


    }


}
