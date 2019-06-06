package com.example.kotlinfirstapp.router

import android.os.Bundle

interface Router {

    fun goBackInActivity()

    fun goBackInFragment()

    fun goToMainPage()

    fun goToRegisterPage()

    fun displayViewPagerFragment()

    fun goToDetailsPage(bundle: Bundle)
}