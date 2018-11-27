package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentMapLayoutBinding
import kotlinx.android.synthetic.main.fragment_map_layout.*
import me.goldze.mvvmhabit.base.BaseFragment
import me.goldze.mvvmhabit.utils.StatusBarUtil



class MapFragment : BaseFragment<FragmentMapLayoutBinding, MapViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_map_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
    override fun initData() {
        super.initData()
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, toolBar)
    }

     override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        map.onDestroy()
    }

     override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
         map.onResume()
    }

     override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
         map.onPause()
    }

    companion object {
        fun newInstance() : MapFragment{
            return MapFragment()
        }
    }
}