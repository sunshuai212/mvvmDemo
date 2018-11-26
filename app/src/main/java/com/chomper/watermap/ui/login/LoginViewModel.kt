package com.chomper.watermap.ui.login

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import com.chomper.watermap.application.WaterApplication
import com.chomper.watermap.di.LiveApiService
import com.chomper.watermap.ui.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.goldze.mvvmhabit.base.BaseViewModel
import javax.inject.Inject


class LoginViewModel(application: Application) : BaseViewModel(application) {

    var userName = ObservableField("")

    var userPaw = ObservableField("")

    @Inject
    lateinit var liveApiService: LiveApiService
    init {
        WaterApplication.get()?.apiComponent?.inject(this)
        liveApiService.login("13701977270", "lc@123456")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {  }
    }
    fun onLogin(view: View) {
        startActivity(MainActivity::class.java)
    }

}