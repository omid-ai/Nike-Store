package com.example.nikestore.common

import android.app.Application
import android.os.Bundle
import com.example.nikestore.data.repo.BannerRepository
import com.example.nikestore.data.repo.BannerRepositoryImpl
import com.example.nikestore.data.repo.ProductRepository
import com.example.nikestore.data.repo.ProductRepositoryImpl
import com.example.nikestore.data.repo.source.BannerRemoteDataSource
import com.example.nikestore.data.repo.source.ProductLocalDataSource
import com.example.nikestore.data.repo.source.ProductRemoteDataSource
import com.example.nikestore.services.FrescoImageLoadingServiceImpl
import com.example.nikestore.services.ImageLoadingService
import com.example.nikestore.services.createApiServiceInstance
import com.example.nikestore.ui.main.HomeViewModel
import com.example.nikestore.ui.product.ProductDetailViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)

        val nikeModule = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingServiceImpl() }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            factory<ProductRepository> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }
            viewModel { HomeViewModel(get(),get()) }
            viewModel {(bundle: Bundle)-> ProductDetailViewModel(bundle) }
        }

        startKoin {
            androidContext(this@App)
            modules(nikeModule)
        }
    }
}