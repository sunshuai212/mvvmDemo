package com.chomper.watermap.ui.data

import android.app.Application
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.entity.DetailData
import com.chomper.watermap.entity.HomeData
import com.chomper.watermap.entity.LineData
import me.goldze.mvvmhabit.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*

class DataDetailViewModel(application: Application) : BaseViewModel(application) {


    var detailDataLit = ObservableArrayList<DetailData>()

    var name = ObservableField<String>("")

    var itemBinding = ItemBinding.of<DetailData>(BR.item, R.layout.item_detail_layout)


    var refreshData = MutableLiveData<Boolean>()

    var btnItemBinding = ItemBinding.of<DetailData>(BR.item, R.layout.item_btn_chart_layout)
        .bindExtra(BR.listener, object : OnItemClickListener {
            override fun onItemClick(item: DetailData) {
                if (item.select) {
                    return
                } else {
                    for (detail in detailDataLit) {
                        detail.select = false
                    }
                    item.select = true
                }
                refreshData.value = true
                randomChartData()
            }
        })

    val lineDataList = MutableLiveData<ArrayList<LineData>>()

    fun initData(homeData: HomeData) {
        name.set(homeData.name)
        val dataList = ArrayList<DetailData>()
        for (i in 1..7) {
            dataList.add(DetailData().apply {
                name = "测试$i"
                result = "$i.4"
                unit = "$i 222"
                select = i == 1
            })

        }
        detailDataLit.addAll(dataList)

        randomChartData()

    }

    fun randomChartData() {
        val temLineDataList = ArrayList<LineData>()

        for (i in 1..15) {

            temLineDataList.add(LineData().apply {
                value = Random().nextInt(100).toDouble()
                date = "$i"
            })
        }
        lineDataList.value = temLineDataList
    }

    fun finish(view: View) {
        finish()
    }
    interface OnItemClickListener {
        fun onItemClick(item: DetailData)
    }
}