package com.example.nikestore.data.repo.source

import com.example.nikestore.data.Product
import com.example.nikestore.services.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class ProductRemoteDataSource(private val apiService: ApiService):ProductDataSource {

    override fun getProduct(sort:Int): Single<List<Product>> =
        apiService.getProduct(sort.toString())

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