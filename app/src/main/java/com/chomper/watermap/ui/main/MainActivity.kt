package com.chomper.watermap.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chomper.watermap.R
import com.chomper.watermap.application.WaterApplication
import com.chomper.watermap.di.LiveApiService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var liveApiService: LiveApiService

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WaterApplication.get()?.apiComponent?.inject(this)

    }
}
