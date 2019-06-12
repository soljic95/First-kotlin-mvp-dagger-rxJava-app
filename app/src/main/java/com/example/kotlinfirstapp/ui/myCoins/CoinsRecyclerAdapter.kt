package com.example.kotlinfirstapp.ui.myCoins

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.kotlinfirstapp.R
import com.example.kotlinfirstapp.model.Data

class CoinsRecyclerAdapter(inflater: LayoutInflater) : RecyclerView.Adapter<CoinsRecyclerAdapter.MyViewHolder>() {

    private var coinList: ArrayList<Data> = ArrayList()
    private var layoutInflater = inflater
    private lateinit var recyclerViewListener: RecyclerViewOnClickListener

    fun addCoin(coin: Data) {
        if (!coinList.contains(coin)) {
            coinList.add(coin)
            Log.d("doesnt contain", coin.name)
            notifyDataSetChanged()
        } else Log.d("marko", "same coin, wont push")
    }

    fun clear() {
        coinList.clear()
        notifyDataSetChanged()
    }

    fun setOnClickListener(RecyclerViewOnClickListener: RecyclerViewOnClickListener) {
        this.recyclerViewListener = RecyclerViewOnClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(layoutInflater.inflate(R.layout.coin_item, parent, false))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val coin = coinList[position]
        holder.tvCoinName.text = coin.name
        holder.tvCoinPrice.text = coin.price
        holder.coin = coin
        holder.listener = recyclerViewListener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.tvCoinName)
        lateinit var tvCoinName: TextView

        @BindView(R.id.tvCoinPrice)
        lateinit var tvCoinPrice: TextView

        @BindView(R.id.coinItemLayout)
        lateinit var layout: ConstraintLayout

        lateinit var coin: Data

        lateinit var listener: RecyclerViewOnClickListener

        @OnClick(R.id.coinItemLayout)
        fun sendData() {
            val bundle = Bundle()
            bundle.putParcelable("coin", coin)
            listener.onItemClickedListener(bundle)

        }
    }


}