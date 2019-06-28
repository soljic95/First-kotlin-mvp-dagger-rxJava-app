package com.example.kotlinfirstapp.ui.loginUser

interface LoginContract {

    interface View {

        fun onWrongPassword()

        fun onDisplayProgressBar()

        fun onHideProgressBar()

        fun onNoUserInDb()


    }

    interface Presenter {

        fun setLoginView(loginView: View)

        fun registerClicked()

        fun loginClicked(userName: String, password: String)


    }
}