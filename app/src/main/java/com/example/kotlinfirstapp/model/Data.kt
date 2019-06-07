package com.example.kotlinfirstapp.model

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(acronym)
        parcel.writeInt(blocks)
        parcel.writeString(hashrate)
        parcel.writeString(mining_difficulty)
        parcel.writeString(name)
        parcel.writeString(network)
        parcel.writeString(price)
        parcel.writeString(price_base)
        parcel.writeInt(price_update_time)
        parcel.writeString(symbol_htmlcode)
        parcel.writeInt(unconfirmed_txs)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}