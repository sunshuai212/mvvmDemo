package com.chomper.watermap.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.application.WaterApplication
import com.chomper.watermap.databinding.FragmentMapLayoutBinding
import com.chomper.watermap.entity.MapData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_map_layout.*
import me.goldze.mvvmhabit.base.BaseFragment
import me.goldze.mvvmhabit.utils.ConvertUtils
import me.goldze.mvvmhabit.utils.StatusBarUtil


class MapFragment : BaseFragment<FragmentMapLayoutBinding, MapViewModel>() {
    var markType = 1
    var arrayList: ArrayList<MapData>? = null
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_map_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        StatusBarUtil.setTranslucentForImageViewInFragment(activity, toolBar)
        getLocation()
        viewModel.mapDataList.observe(this, Observer {
            addMarkerView(it, true)
        })
        viewModel.showDrawerLayout.observe(this, Observer {
            //            drawerLayout.openDrawer(GravityCompat.START)
            val main = activity as MainActivity
            main.drawerLayout.openDrawer(GravityCompat.START)
        })
    }

    private fun addMarkerView(list: ArrayList<MapData>?, isSmall: Boolean) {
        if (list != null) {
            map.map.clear()
            arrayList = list
            val options = ArrayList<OverlayOptions>()
            for (item in list) {
                options.add(
                    MarkerOptions().position(LatLng(item.lat, item.long))
                        .extraInfo(Bundle().apply {
                            putParcelable("data", item)
                        })
                        .apply {
                            if (isSmall) {
                                this.icon(
                                    BitmapDescriptorFactory
                                        .fromResource(com.chomper.watermap.R.mipmap.conetnt_icon_dot)
                                )
                            } else {
                                if (item.state == 1) {
                                    val option = BitmapFactory.Options()
                                    option.outHeight = ConvertUtils.dp2px(10f)
                                    option.outWidth = ConvertUtils.dp2px(10f)

                                    val temBitmap =
                                       Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.mipmap.icon_online, option),ConvertUtils.dp2px(30f),ConvertUtils.dp2px(30f),false)
                                    this.icon(
                                        BitmapDescriptorFactory
                                            .fromBitmap(temBitmap)
                                    )
                                } else {
                                    val option = BitmapFactory.Options()
                                    option.outHeight = ConvertUtils.dp2px(10f)
                                    option.outWidth = ConvertUtils.dp2px(10f)
                                    val temBitmap =
                                        Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.mipmap.icon_online, option),ConvertUtils.dp2px(30f),ConvertUtils.dp2px(30f),false)
                                    this.icon(
                                        BitmapDescriptorFactory
                                            .fromBitmap(temBitmap)
                                    )
                                }
                            }
                        }
                )

            }

            //在地图上批量添加
            map.map.addOverlays(options)
        }

    }

    private fun getLocation() {
        map.map.isMyLocationEnabled = true
        initLocationOption()
        map.map.setOnMapStatusChangeListener(object : BaiduMap.OnMapStatusChangeListener {
            override fun onMapStatusChangeStart(p0: MapStatus?) {

            }

            override fun onMapStatusChangeStart(p0: MapStatus?, p1: Int) {
            }

            override fun onMapStatusChange(p0: MapStatus?) {
            }

            override fun onMapStatusChangeFinish(p0: MapStatus?) {
                Log.d("sunshuai", "${p0?.zoom}")
                refreshMarke(p0?.zoom)
            }

        })
        map.map.setOnMarkerClickListener {
            if (markType == 2) {
                val view = layoutInflater.inflate(com.chomper.watermap.R.layout.map_info_window, null)

                //定义用于显示该InfoWindow的坐标点
                val pt = it.position

//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                val mInfoWindow = InfoWindow(view, pt, -87)

//显示InfoWindow
                map.map.showInfoWindow(mInfoWindow)
            }
            true
        }
        map.map.setOnMapClickListener(object : BaiduMap.OnMapClickListener {
            override fun onMapPoiClick(p0: MapPoi?): Boolean {
                return false
            }

            override fun onMapClick(p0: LatLng?) {
                map.map.hideInfoWindow()
            }

        })
    }

    private fun refreshMarke(zoom: Float?) {
        zoom?.apply {
            if (this <= 13 && markType != 1) {
                markType = 1
                addMarkerView(arrayList, true)

            } else if (this >= 14 && markType != 2) {
                markType = 2
                addMarkerView(arrayList, false)

            }
        }
    }

    private lateinit var locationClient: LocationClient

    /**
     * 初始化定位参数配置
     */

    private fun initLocationOption() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        locationClient = LocationClient(WaterApplication.get())
        //声明LocationClient类实例并配置定位参数
        val locationOption = LocationClientOption()
        val myLocationListener = MyLocationListener()
        //注册监听函数
        locationClient.registerLocationListener(myLocationListener)
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll")
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000)
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true)
        //可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true)
        //可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false)
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.isLocationNotify = true
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true)
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true)
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true)
        //可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false)
        //可选，默认false，设置是否开启Gps定位
        locationOption.isOpenGps = true
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false)
        //开始定位
        locationClient.start()
    }

    /**
     * 实现定位回调
     */
    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            val latitude = location.latitude
            //获取经度信息
            val longitude = location.longitude
            Log.d("sunshuai", "lat$latitude  long$longitude")

            val locData = MyLocationData.Builder()
                .accuracy(location.radius)
                .direction(location.radius)
                .latitude(location.latitude)
                .longitude(location.longitude).build()
            map.map.setMyLocationData(locData)

            val builder = MapStatus.Builder()
            builder.target(LatLng(latitude, longitude))
            map.map.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))


            //获取定位精度，默认值为0.0f
            val radius = location.radius
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            val coorType = location.coorType
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            val errorCode = location.locType

            locationClient.stop()
        }

        override fun onConnectHotSpotMessage(p0: String?, p1: Int) {
            super.onConnectHotSpotMessage(p0, p1)
        }

        override fun onLocDiagnosticMessage(p0: Int, p1: Int, p2: String?) {
            super.onLocDiagnosticMessage(p0, p1, p2)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        map.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        map.onPause()
    }

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }
}