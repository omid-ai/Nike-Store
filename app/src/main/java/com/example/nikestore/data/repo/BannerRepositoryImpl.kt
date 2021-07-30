package com.example.nikestore.data.repo

import com.example.nikestore.data.Banner
import com.example.nikestore.data.repo.source.BannerDataSource
import com.example.nikestore.data.repo.source.BannerRemoteDataSource
import io.reactivex.Single

class BannerRepositoryImpl(private val remoteDataSource: BannerDataSource):BannerRepository {
    override fun getBanner(): Single<List<Banner>> = remoteDataSource.getBanner()
}