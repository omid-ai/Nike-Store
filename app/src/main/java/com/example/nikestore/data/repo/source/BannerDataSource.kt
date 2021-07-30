package com.example.nikestore.data.repo.source

import com.example.nikestore.data.Banner
import io.reactivex.Single

interface BannerDataSource {

    fun getBanner():Single<List<Banner>>
}