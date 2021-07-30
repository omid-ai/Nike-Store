package com.example.nikestore.services

import com.example.nikestore.data.Banner
import com.example.nikestore.data.Product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("product/list")
    fun getProduct(@Query("sort")sort:String):Single<List<Product>>

    @GET("banner/slider")
    fun getBanner():Single<List<Banner>>
}

fun createApiServiceInstance():ApiService{
    val retrofit=Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .build()
    return retrofit.create(ApiService::class.java)
}