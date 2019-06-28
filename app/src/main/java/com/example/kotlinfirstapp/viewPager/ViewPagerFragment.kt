package com.example.kotlinfirstapp.viewPager


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.base.BaseFragment
import com.example.kotlinfirstapp.base.BasePresenter
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.main.MainActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.view_pager_layout.*
import javax.inject.Inject


class ViewPagerFragment : BaseFragment(), ViewPagerContract.View {
    companion object {
        @JvmStatic
        fun newInstance(): ViewPagerFragment {
            return ViewPagerFragment()
        }
    }

    @Inject
    lateinit var presenter: ViewPagerContract.Presenter

    private var savedInstance: Bundle? = null
    private var progressDialog: AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.view_pager_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.init()
        savedInstance = savedInstanceState
        setHasOptionsMenu(true)
        createProgressBar()
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_logout -> {
                Log.d("marko", "logout pressed")
                presenter.logOutClicked()
            }
            R.id.menu_item_deleteAccount -> Log.d("marko", "delete acc pressed")
        }

        return false
    }

    override fun displayProgressDialog() {
        progressDialog?.show()
    }


    override fun onResume() {
        val actionBar = (activity as MainActivity).getSupportAction()
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = getString(R.string.coin_search)
        super.onResume()

    }

    private fun createProgressBar() {
        progressDialog = AlertDialog.Builder(context!!)
            .setView(R.layout.progress_dialog)
            .create()
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }


}
