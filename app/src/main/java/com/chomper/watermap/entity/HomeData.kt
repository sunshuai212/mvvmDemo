package com.chomper.watermap.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonArray

class HomeData : Parcelable {

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

        @JvmField
        val CREATOR = object : Parcelable.Creator<HomeData> {
            override fun createFromParcel(parcel: Parcel): HomeData {
                return HomeData(parcel)
            }

            override fun newArray(size: Int): Array<HomeData?> {
                return arrayOfNulls(size)
            }
        }
    }

    var id: Long = 0
    var name: String = ""
    var address: String = ""
    var state: Int = 0

    constructor() {
    }


    constructor(parcel: Parcel) {
        id = parcel.readLong()
        name = parcel.readString()
        address = parcel.readString()
        state = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeInt(state)
    }

    override fun describeContents(): Int {
        return 0
    }
}