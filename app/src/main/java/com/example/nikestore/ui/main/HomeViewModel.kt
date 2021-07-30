package com.example.nikestore.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.nikestore.common.NikeSingleObserver
import com.example.nikestore.common.NikeViewModel
import com.example.nikestore.data.Banner
import com.example.nikestore.data.Product
import com.example.nikestore.data.SORT_LATEST
import com.example.nikestore.data.SORT_POPULAR
import com.example.nikestore.data.repo.BannerRepository
import com.example.nikestore.data.repo.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel( productRepository: ProductRepository,bannerRepository: BannerRepository):NikeViewModel() {

    val latestProductLiveData=MutableLiveData<List<Product>>()
    val popularProductLiveData=MutableLiveData<List<Product>>()
    val bannerLiveData=MutableLiveData<List<Banner>>()

    init {
        progressBarLiveData.value=true
        productRepository.getProduct(SORT_LATEST).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { progressBarLiveData.value=false }
            .subscribe(object :NikeSingleObserver<List<Product>>(composDisposable){
                override fun onSuccess(t: List<Product>) {
                    latestProductLiveData.value=t
                }
            })

        productRepository.getProduct(SORT_POPULAR).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :NikeSingleObserver<List<Product>>(composDisposable){
                override fun onSuccess(t: List<Product>) {
                    popularProductLiveData.value=t
                }
            })

        bannerRepository.getBanner().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :NikeSingleObserver<List<Banner>>(composDisposable){
                override fun onSuccess(t: List<Banner>) {
                    bannerLiveData.value=t
                }
            })
    }
}