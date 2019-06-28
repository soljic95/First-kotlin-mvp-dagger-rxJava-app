package com.example.kotlinfirstapp.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MyViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitle: MutableList<String> = ArrayList()


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size

    }

    fun addFragment(fragment: Fragment, fragName: String) {
        fragmentList.add(fragment)
        fragmentTitle.add(fragName)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }

    fun clear() {
        fragmentTitle.clear()
        fragmentList.clear()
    }


}