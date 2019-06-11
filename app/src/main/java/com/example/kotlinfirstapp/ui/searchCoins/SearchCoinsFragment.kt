package com.example.kotlinfirstapp.ui.searchCoins


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import kotlinx.android.synthetic.main.fragment_search_coins.*
import javax.inject.Inject


class SearchCoinsFragment : BaseFragment(), SearchCoinsContract.View {


    @Inject
    lateinit var presenter: SearchCoinsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @OnClick(R.id.btnSearch)
    fun onBtnSearchClick() {
        presenter.btnSearchClicked(etCoinName.text.toString())
    }

    @OnClick(R.id.btnDetails)
    fun onDetailsBtnClicked() {
        presenter.btnDetailsClicked()
    }

    override fun onDisplayProgressBar() {
        searchProgressBar.visibility = View.VISIBLE
    }

    override fun onHideProgressBar() {
        searchProgressBar.visibility = View.INVISIBLE
    }

    override fun onCoinReady(coinName: String) {
        tvCoinName.text = coinName
        btnDetails.visibility = View.VISIBLE
    }

    override fun onPause() {
        childFragmentManager.beginTransaction().addToBackStack(resources.getString(R.string.search_coins_tag)).commit()
        super.onPause()
    }

    override fun onResume() {
        presenter.setSearchCoinsView(this)
        super.onResume()
    }

    override fun onError() {
        Toast.makeText(context, resources.getString(R.string.retrofit_error), Toast.LENGTH_SHORT).show()
        presenter.hideProgressBar()
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }


}
