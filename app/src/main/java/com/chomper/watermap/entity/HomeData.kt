package com.chomper.watermap.entity

import com.google.gson.JsonArray
import java.io.Serializable

class HomeData : Serializable{

    companion object {
        const val OFFLINE = 1

        @JvmStatic
        fun convertJsonData(jsonArray: JsonArray): ArrayList<HomeData> {
            val dataList = ArrayList<HomeData>()
            for (array in jsonArray) {
                if (array is JsonArray) {
                    dataList.add(HomeData().apply {
                        id = array.get(0).asLong
                        name = array.get(1).asString
                        address = array.get(2).asString
                        state = array.get(3).asInt
                    })
                }
            }
            return dataList
        }
    }

    var id: Long = 0
    var name: String = ""
    var address: String = ""
    var state: Int = 0

}