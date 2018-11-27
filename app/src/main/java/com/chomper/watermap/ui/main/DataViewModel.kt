package com.chomper.watermap.ui.main

import android.app.Application
import android.app.DatePickerDialog
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import me.goldze.mvvmhabit.base.BaseViewModel
import java.util.*

class DataViewModel(application: Application) : BaseViewModel(application) {

    var dateList = MutableLiveData<ArrayList<String>>()

    var headerList = MutableLiveData<ArrayList<String>>()

    var hirstoryList = MutableLiveData<ArrayList<ArrayList<String>>>()

    var startDate = ObservableField<String>("2018-11-25")
    var endDate = ObservableField<String>("2018-12-09")

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

    fun onStartTime(view: View) {

        val ca = Calendar.getInstance()
        val mYear = ca.get(Calendar.YEAR)
        val mMonth = ca.get(Calendar.MONTH)
        val mDay = ca.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            view.context,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                run {
                    startDate.set("$year-$month-$dayOfMonth")
                }
            },
            mYear, mMonth, mDay
        )
        datePickerDialog.show()

    }

    fun onEndTime(view: View) {
        val ca = Calendar.getInstance()
        val mYear = ca.get(Calendar.YEAR)
        val mMonth = ca.get(Calendar.MONTH)
        val mDay = ca.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            view.context,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                run {
                    endDate.set("$year-$month-$dayOfMonth")
                }
            },
            mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    fun onSearch(view: View) {

    }
}