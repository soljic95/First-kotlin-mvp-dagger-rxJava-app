package com.example.kotlinfirstapp.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.viewPager.ViewPagerFragment
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsFragment
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserActivity

class RouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager
) : Router {


    override fun displayViewPagerFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.frag_container, ViewPagerFragment(), "view_pager_fragment")
            .commit()
    }

    override fun goBackInFragment() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

    override fun goBackInActivity() {
        activity.finish()
    }

    override fun goToMainPage() {
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    override fun goToRegisterPage() {
        activity.startActivity(Intent(activity, RegisterUserActivity::class.java))
    }

    override fun goToDetailsPage(bundle: Bundle) {
        var detailsFragment = CoinDetailsFragment()
        detailsFragment.arguments = bundle
        fragmentManager.beginTransaction().addToBackStack("view_pager_fragment")
            .replace(R.id.frag_container, detailsFragment).commit()
    }
}