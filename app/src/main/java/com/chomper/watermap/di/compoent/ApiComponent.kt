package com.chomper.watermap.di.compoent


import com.bilibili.svgatools.di.module.LiveVideoModuel
import com.chomper.watermap.di.scope.ApplicationScope
import com.chomper.watermap.ui.login.LoginViewModel
import com.chomper.watermap.ui.main.HomeFragment
import com.chomper.watermap.ui.main.HomeViewModel
import dagger.Subcomponent

@ApplicationScope
@Subcomponent(modules = [LiveVideoModuel::class])
interface ApiComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(homeViewModel: HomeViewModel)
}