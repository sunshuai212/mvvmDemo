package com.chomper.watermap.entity

import android.os.Parcel
import android.os.Parcelable

class MapData() : Parcelable {

    var no: String = ""
    var name: String = ""
    var address: String = ""
    var state: Int = 0
    var lat: Double = Double.MIN_VALUE
    var long: Double = Double.MIN_VALUE
    var time: String = ""

    constructor(parcel: Parcel) : this() {
        no = parcel.readString()
        name = parcel.readString()
        address = parcel.readString()
        state = parcel.readInt()
        lat = parcel.readDouble()
        long = parcel.readDouble()
        time = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(no)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeInt(state)
        parcel.writeDouble(lat)
        parcel.writeDouble(long)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @JvmField
        val CREATOR = object
            : Parcelable.Creator<MapData> {
            override fun createFromParcel(parcel: Parcel): MapData {
                return MapData(parcel)
            }

            override fun newArray(size: Int): Array<MapData?> {
                return arrayOfNulls(size)
            }
        }
    }

}