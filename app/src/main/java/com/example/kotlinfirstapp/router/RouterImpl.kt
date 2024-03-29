package com.example.kotlinfirstapp.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.ui.coinDetails.CoinDetailsFragment
import com.example.kotlinfirstapp.ui.loginUser.LoginActivity
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserActivity
import com.example.kotlinfirstapp.viewPager.ViewPagerFragment

class RouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager
) : Router {


    private inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    private inline fun FragmentManager.doTransactionAndAddToBackStack(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().addToBackStack(null).commit()
    }


    override fun displayViewPagerFragment() {
        fragmentManager.doTransaction { replace(R.id.frag_container, ViewPagerFragment.newInstance()) }
    }

    override fun goToDetailsPage(bundle: Bundle) {
        fragmentManager.doTransactionAndAddToBackStack {
            replace(
                R.id.frag_container,
                CoinDetailsFragment.newInstance(bundle)
            )
        }
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
        activity.finish()
    }


    override fun goToLoginPage() {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

}