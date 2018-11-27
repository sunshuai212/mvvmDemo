package com.chomper.watermap.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.FragmentDataLayoutBinding
import com.chomper.watermap.ui.adapter.HirstoeyAdapter
import com.chomper.watermap.ui.adapter.HirstoryDataHeader
import kotlinx.android.synthetic.main.fragment_data_layout.*
import me.goldze.mvvmhabit.base.BaseFragment
import me.goldze.mvvmhabit.binding.viewadapter.recyclerview.DividerLine
import me.goldze.mvvmhabit.utils.StatusBarUtil

class DataFragment : BaseFragment<FragmentDataLayoutBinding, DataViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_data_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    companion object {
        fun newInstance(): DataFragment {
            return DataFragment()
        }
    }

    override fun initData() {
        super.initData()
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, toolBar)
        rv_date.layoutManager = LinearLayoutManager(context)
        rv_hirstory.layoutManager = LinearLayoutManager(context)
        rv_date.overScrollMode = View.OVER_SCROLL_NEVER
        rv_hirstory.overScrollMode = View.OVER_SCROLL_NEVER

        rv_date.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    rv_hirstory.scrollBy(dx, dy)
                }
            }
        })
        rv_hirstory.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
                    rv_date.scrollBy(dx, dy)
                }
            }
        })
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.dateList.observe(this, Observer {
            rv_date.adapter = HirstoryDataHeader(it)
        })
        viewModel.headerList.observe(this, Observer {
            head_container.removeAllViews()
            val params = LinearLayout.LayoutParams(DividerLine.dip2px(context, 100f), DividerLine.dip2px(context, 35f))
            params.gravity = Gravity.CENTER
            for (item in it) {
                head_container.addView(View.inflate(context, R.layout.item_hirstory_data_header, null).apply {
                    if (this is TextView) {
                        text = item
                    }
                }, params)
            }
        })
        viewModel.hirstoryList.observe(this, Observer {
            rv_hirstory.adapter = HirstoeyAdapter(it)
        })
    }

}