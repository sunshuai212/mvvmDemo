package com.chomper.watermap.di.compoent

import com.chomper.watermap.di.module.BaseModule
import com.bilibili.svgatools.di.module.LiveVideoModuel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {
    fun plus(liveVideoModule: LiveVideoModuel): ApiComponent
}