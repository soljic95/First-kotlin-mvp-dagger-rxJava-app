package com.example.kotlinfirstapp.viewPager


import android.os.Bundle
import android.view.*
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.adapter.MyViewPagerAdapter
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.di.component.FragmentComponent
import com.example.kotlinfirstapp.main.MainActivity
import kotlinx.android.synthetic.main.view_pager_layout.*
import javax.inject.Inject


class ViewPagerFragment : BaseFragment(), ViewPagerContract.View {


    @Inject
    lateinit var presenter: ViewPagerContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.view_pager_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setHasOptionsMenu(true)
    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onAdapterReady(adapter: MyViewPagerAdapter) {
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_frag_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        var actionBar = (activity as MainActivity).getSupportAction()
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = "CoinSearch"
        presenter.init()

        super.onResume()
    }


}
