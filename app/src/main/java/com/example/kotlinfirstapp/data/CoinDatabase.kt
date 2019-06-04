package com.example.kotlinfirstapp.data

import android.app.Application

abstract class CoinDatabase(app: Application) {

    var coinDatabase: CoinDatabase? = null


}