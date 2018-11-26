package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentMapLayoutBinding
import me.goldze.mvvmhabit.base.BaseFragment

class MapFragment : BaseFragment<FragmentMapLayoutBinding, MapViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_map_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


    companion object {
        fun newInstance() : MapFragment{
            return MapFragment()
        }
    }
}