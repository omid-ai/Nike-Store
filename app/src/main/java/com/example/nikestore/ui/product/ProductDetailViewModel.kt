package com.example.nikestore.ui.product

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.nikestore.common.EXTRA_KEY_DATA
import com.example.nikestore.common.NikeViewModel
import com.example.nikestore.data.Product

class ProductDetailViewModel(bundle: Bundle):NikeViewModel() {

    val productDetailLiveData=MutableLiveData<Product>()

    init {
        productDetailLiveData.value=bundle.getParcelable(EXTRA_KEY_DATA)
    }
}