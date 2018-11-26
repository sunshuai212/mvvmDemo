package com.chomper.watermap.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import me.goldze.mvvmhabit.base.BaseViewModel

class DataViewModel(application: Application) : BaseViewModel(application) {

    var dateList = MutableLiveData<ArrayList<String>>()

    var headerList = MutableLiveData<ArrayList<String>>()

    var hirstoryList = MutableLiveData<ArrayList<ArrayList<String>>>()

    init {
        val temDateList = ArrayList<String>()
        val temHeaderList = ArrayList<String>()
        val temHistoryList = ArrayList<ArrayList<String>>()
        for (i in 1..30) {
            temDateList.add("2018-${i}")
            if (i <= 10) {
                temHeaderList.add("测试$i")
            }
            val temHistory = ArrayList<String>()
            for (m in 1..10) {
                temHistory.add("${m * 2}.2")
            }
            temHistoryList.add(temHistory)
        }
        headerList.value = temHeaderList
        dateList.value = temDateList
        hirstoryList.value = temHistoryList
    }
}