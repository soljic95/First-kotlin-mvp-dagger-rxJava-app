package com.example.kotlinfirstapp.ui.myCoins


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.model.Data
import kotlinx.android.synthetic.main.fragment_my_coins.*
import javax.inject.Inject
import javax.inject.Provider


class MyCoinsFragment : BaseFragment(), MyCoinsContract.View, RecyclerViewOnClickListener {


    @Inject
    lateinit var presenter: MyCoinsContract.Presenter

    @Inject
    lateinit var coinsRecyclerAdapter: CoinsRecyclerAdapter

    /*@Inject
    lateinit var manager: Provider<LinearLayoutManager>*/


    @BindView(R.id.recyclerViewContainer)
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_my_coins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        presenter.setUpView(this)
        setRecyclerView()
        presenter.init()

    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCoinsReady(listOfCoins: ArrayList<Data>) {
        coinsRecyclerAdapter.clear()
        for (coin in listOfCoins) {
            coinsRecyclerAdapter.addCoin(coin)
            coinsRecyclerAdapter.notifyDataSetChanged()

        }
        presenter.hideProgressBar()
    }

    override fun onHideProgressBar() {
        myCoinsProgressBar.visibility = View.INVISIBLE

    }

    override fun onDisplayProgressBar() {
        myCoinsProgressBar.visibility = View.VISIBLE
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

    private fun setRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        coinsRecyclerAdapter.setOnClickListener(this)
        recyclerView.adapter = coinsRecyclerAdapter

    }

    override fun onItemClickedListener(bundle: Bundle) {
        presenter.onRecyclerItemClicked(bundle)
    }


}
