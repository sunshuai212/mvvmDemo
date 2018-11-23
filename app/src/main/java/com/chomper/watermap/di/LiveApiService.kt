package com.chomper.watermap.di

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LiveApiService {

    @FormUrlEncoded
    @POST("/webapp/api/account/verifications")
    fun login(@Field("name") name: String, @Field("password") pwd: String): Observable<JsonObject>

    @FormUrlEncoded
    @POST("/webapp/api/appapi")
    fun getSiteList(@Field("type") type: String): Observable<JsonArray>

}