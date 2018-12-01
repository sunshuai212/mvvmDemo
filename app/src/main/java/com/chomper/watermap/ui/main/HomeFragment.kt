package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentHomeLayoutBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_layout.*
import me.goldze.mvvmhabit.base.BaseFragment
import me.goldze.mvvmhabit.utils.StatusBarUtil
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeViewModel>() {

    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_home_layout
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showDrawerLayout.observe(this, Observer {
            //            drawerLayout.openDrawer(GravityCompat.START)
            val main = activity as MainActivity
            main.drawerLayout.openDrawer(GravityCompat.START)
        })
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, toolBar)
        swrefresh.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        swrefresh.setOnRefreshListener {
            Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    swrefresh.isRefreshing = false
                }
        }
    }


    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}