package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentHomeLayoutBinding
import kotlinx.android.synthetic.main.fragment_home_layout.*
import me.goldze.mvvmhabit.base.BaseFragment
import me.goldze.mvvmhabit.utils.StatusBarUtil

class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeViewModel>() {


    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_home_layout
    }

    override fun initViewObservable() {
        super.initViewObservable()
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, toolBar)
    }


    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}