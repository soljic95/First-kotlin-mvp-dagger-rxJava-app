package com.example.kotlinfirstapp.router

import android.app.Activity
import android.content.Intent
import com.example.kotlinfirstapp.main.MainActivity
import com.example.kotlinfirstapp.ui.registerUser.RegisterUserActivity

class RouterImpl(val activity: Activity) : Router {


    override fun goBack() {
        activity.finish()
    }

    override fun goToMainPage() {
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    override fun goToRegisterPage() {
        activity.startActivity(Intent(activity, RegisterUserActivity::class.java))
    }
}