package com.example.kotlinfirstapp.model

data class Data(
    val acronym: String,
    val blocks: Int,
    val hashrate: String,
    val mining_difficulty: String,
    val name: String,
    val network: String,
    val price: String,
    val price_base: String,
    val price_update_time: Int,
    val symbol_htmlcode: String,
    val unconfirmed_txs: Int,
    val url: String
)