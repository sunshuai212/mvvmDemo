package com.bilibili.svgatools.di.module

import com.chomper.watermap.di.LiveApiService
import com.chomper.watermap.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LiveVideoModuel {

    @ApplicationScope
    @Provides
    fun provideLiveApiService(retrofit: Retrofit): LiveApiService = retrofit.create(LiveApiService::class.java)
}