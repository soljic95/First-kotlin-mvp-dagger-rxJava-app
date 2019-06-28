package com.example.kotlinfirstapp.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_info")
data class Data(
    @PrimaryKey
    @ColumnInfo(name = "coin_name")
    val name: String,
    @ColumnInfo(name = "coin_acronym")
    val acronym: String,
    @ColumnInfo(name = "coin_block")
    val blocks: Int,
    @ColumnInfo(name = "coin_hashrate")
    val hashrate: String,
    @ColumnInfo(name = "coin_mining_difficulty")
    val mining_difficulty: String,
    @ColumnInfo(name = "coin_network")
    val network: String,
    @ColumnInfo(name = "coin_price")
    val price: String,
    @ColumnInfo(name = "coin_price_base")
    val price_base: String,
    @ColumnInfo(name = "coin_price_update_time")
    val price_update_time: Int,
    @ColumnInfo(name = "coin_symbol_htmlcode")
    val symbol_htmlcode: String,
    @ColumnInfo(name = "coin_unconfirmed_txs")
    val unconfirmed_txs: Int,
    @ColumnInfo(name = "coin_url")
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(acronym)
        parcel.writeInt(blocks)
        parcel.writeString(hashrate)
        parcel.writeString(mining_difficulty)
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