package com.example.nikestore.data.repo.source

import com.example.nikestore.data.Product
import io.reactivex.Completable
import io.reactivex.Single

class ProductLocalDataSource:ProductDataSource {
    override fun getProduct(sort:Int): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun addToFavorites(): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteFromFavorites(): Completable {
        TODO("Not yet implemented")
    }
}