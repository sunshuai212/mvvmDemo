package com.chomper.watermap.ui.login

import android.os.Bundle
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.ActivityLoginBinding
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

}
