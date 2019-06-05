package com.example.kotlinfirstapp.ui.registerUser


interface RegisterUserContract {

    interface View {

        fun onShowProgressBar()

        fun onHideProgressBar()

        fun onErrorWhileInserting()

        fun onUserNameError()

        fun onPasswordError()

    }

    interface Presenter {

        fun registerUserClicked(userName: String, password: String) //possibly send data through a bundle maybe?

        fun setRegisterView(registerView: View)

        fun showProgressBar()

        fun hideProgressBar()

        fun displayErrorWhileInserting()
    }
}