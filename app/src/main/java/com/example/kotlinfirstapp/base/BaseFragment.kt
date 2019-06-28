package com.example.kotlinfirstapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlinfirstapp.dagger.component.DaggerFragmentComponent
import com.example.kotlinfirstapp.dagger.component.FragmentComponent
import com.example.kotlinfirstapp.dagger.module.FragmentModule

abstract class BaseFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = DaggerFragmentComponent.builder()
            .activityComponent((activity as BaseActivity).getComponent())
            .fragmentModule(FragmentModule(childFragmentManager))
            .build()

        inject(fragmentComponent)

    }

    abstract fun inject(fragmentComponent: FragmentComponent)
}