package com.chomper.watermap.di.module

import com.chomper.watermap.BuildConfig
import com.chomper.watermap.application.WaterApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.goldze.mvvmhabit.http.cookie.CookieJarImpl
import me.goldze.mvvmhabit.http.cookie.store.PersistentCookieStore
import me.goldze.mvvmhabit.http.interceptor.CacheInterceptor
import me.goldze.mvvmhabit.http.interceptor.logging.Level
import me.goldze.mvvmhabit.http.interceptor.logging.LoggingInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class BaseModule {


    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: LoggingInterceptor, cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .cache(cache)
        .cookieJar(CookieJarImpl(PersistentCookieStore(WaterApplication.get())))
        .addInterceptor(CacheInterceptor(WaterApplication.get()))
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("http://101.231.234.236:8081")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideLog() =
        LoggingInterceptor
            .Builder()//构建者模式
            .loggable(true) //是否开启日志打印
            .setLevel(Level.BODY) //打印的等级
            .log(Platform.INFO) // 打印类型
            .request("Request") // request的Tag
            .response("Response")// Response的Tag
            .addHeader("version", BuildConfig.VERSION_NAME)//打印版本
            .build()

    @Provides
    @Singleton
    fun provideCache() : Cache {//缓存时间
        val CACHE_TIMEOUT = 10 * 1024 * 1024L
        //缓存存放的文件
        val httpCacheDirectory = File(WaterApplication.get()?.cacheDir, "goldze_cache")
        //缓存对象
        return Cache(httpCacheDirectory, CACHE_TIMEOUT)
    }

}