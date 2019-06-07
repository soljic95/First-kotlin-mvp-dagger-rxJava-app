package com.example.kotlinfirstapp.ui.myCoins


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinfirstapp.R


class MyCoinsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_coins, container, false)
    }

    override fun onPause() {
        childFragmentManager.beginTransaction().addToBackStack("my_coins_fragment").commit()
        super.onPause()
    }

    override fun onResume() {

        super.onResume()
    }


}
