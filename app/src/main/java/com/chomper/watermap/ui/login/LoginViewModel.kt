package com.chomper.watermap.ui.login

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import com.chomper.watermap.ui.main.MainActivity
import me.goldze.mvvmhabit.base.BaseViewModel


class LoginViewModel(application: Application) : BaseViewModel(application) {

    var userName = ObservableField("")

    var userPaw = ObservableField("")

    fun onLogin(view: View) {
        startActivity(MainActivity::class.java)
    }

}