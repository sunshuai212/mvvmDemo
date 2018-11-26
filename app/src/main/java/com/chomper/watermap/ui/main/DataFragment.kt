package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentDataLayoutBinding
import me.goldze.mvvmhabit.base.BaseFragment

class  DataFragment : BaseFragment<FragmentDataLayoutBinding, DataViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_data_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    companion object {
        fun newInstance() : DataFragment{
            return DataFragment()
        }
    }
}