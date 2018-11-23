package com.chomper.watermap.di.compoent


import com.bilibili.svgatools.di.module.LiveVideoModuel
import com.chomper.watermap.ui.main.MainActivity
import com.chomper.watermap.di.scope.ApplicationScope
import dagger.Subcomponent
import me.goldze.mvvmhabit.base.BaseViewModel

@ApplicationScope
@Subcomponent(modules = [LiveVideoModuel::class])
interface ApiComponent {
    fun inject(baseViewModel: BaseViewModel)
    fun inject(baseViewModel: MainActivity)
}