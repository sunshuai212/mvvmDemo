package com.chomper.watermap.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.ActivityLoginBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import me.goldze.mvvmhabit.base.BaseActivity

/**
 *@author
 * chomper
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    @SuppressLint("CheckResult")
    override fun initData() {
        super.initData()
        val rxPermission = RxPermissions(this)
        rxPermission.request(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { }
    }

}
