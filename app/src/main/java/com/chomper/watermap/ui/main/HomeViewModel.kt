package com.chomper.watermap.ui.main

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.application.WaterApplication
import com.chomper.watermap.di.LiveApiService
import com.chomper.watermap.entity.HomeData
import com.chomper.watermap.ui.data.DataDetailActivity
import com.chomper.watermap.ui.search.SearchActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.utils.KLog
import me.tatarka.bindingcollectionadapter2.ItemBinding.of
import javax.inject.Inject

@SuppressLint("CheckResult")
class HomeViewModel(application: Application) : BaseViewModel(application) {

    var homeDataList = ObservableArrayList<HomeData>()

    var showDrawerLayout = MutableLiveData<Boolean>()

    var itemBinding = of<HomeData>(BR.item, R.layout.item_home_layout)
        .bindExtra(BR.itemClickListener, object : OnItemClickListener {
            override fun onItemClick(item: HomeData) {
                startActivity(DataDetailActivity::class.java, Bundle().apply {
                    putParcelable("data", item)
                })
            }
        })

    @Inject
    lateinit var liveApiService: LiveApiService


    init {
        WaterApplication.get()?.apiComponent?.inject(this)
        loadHomeData()
        liveApiService.getSiteList("getsitelist")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                KLog.d(it)
            }
    }

    private fun loadHomeData() {
        val arrayList = ArrayList<HomeData>()
        for (i in 1..15) {
            arrayList.add(HomeData().apply {
                id = i.toLong()
                name = "测试点 +$i"
                address = "测试地址 + $i"
                state = i % 2
            })
        }
        homeDataList.addAll(arrayList)

    }

    fun onUserClick(view: View) {
        showDrawerLayout.value = true
    }

    fun onSearchClick(view: View) {
        startActivity(SearchActivity::class.java, Bundle().apply {
            putParcelableArrayList("data", homeDataList)
        })
    }

    companion object {

        @JvmStatic
        @BindingAdapter("onlineIcon")
        fun bindOnlineIcon(view: ImageView, state: Int) {
            when (state) {
                1 -> view.setImageResource(R.mipmap.icon_offline)
                else -> view.setImageResource(R.mipmap.icon_online)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: HomeData)
    }
}