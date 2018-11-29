package com.chomper.watermap.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.chomper.watermap.entity.MapData
import me.goldze.mvvmhabit.base.BaseViewModel

class MapViewModel(application: Application) : BaseViewModel(application) {

    val mapDataList = MutableLiveData<ArrayList<MapData>>()

    init {
        val temList = ArrayList<MapData>()
        for (i in 1..20) {
            temList.add(MapData().apply {
                name = ""
                lat = 11.toDouble()
                long = 11.toDouble()
            })
        }
        mapDataList.value = temList
    }
}