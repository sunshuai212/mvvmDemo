package com.chomper.watermap.ui.main

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chomper.watermap.entity.MapData
import me.goldze.mvvmhabit.base.BaseViewModel
import java.util.*

class MapViewModel(application: Application) : BaseViewModel(application) {

    val mapDataList = MutableLiveData<ArrayList<MapData>>()
    var showDrawerLayout = MutableLiveData<Boolean>()

    val mlat = 31.308095
    val mlong = 121.490255

    init {
        val temList = ArrayList<MapData>()
        for (i in 1..100) {
            temList.add(MapData().apply {
                if (i%2 == 0) {
                    name = ""
                    lat = mlat + Random().nextInt(i * 2) * 0.03
                    long = mlong + Random().nextInt(i * 2) * 0.03
                } else {
                    lat = mlat - Random().nextInt(i * 2) * 0.03
                    long = mlong - Random().nextInt(i * 2) * 0.03
                }
            })
        }
        mapDataList.value = temList
    }

    fun onUserClick(view: View) {
        showDrawerLayout.value = true
    }

}