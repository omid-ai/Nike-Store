package com.example.nikestore.ui.product

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikestore.common.formatPrice
import com.example.nikestore.databinding.ActivityProductDetailBinding
import com.example.nikestore.services.ImageLoadingService
import com.example.nikestore.view.observableScrollView.ObservableScrollViewCallbacks
import com.example.nikestore.view.observableScrollView.ScrollState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailBinding
    val viewModel:ProductDetailViewModel by viewModel { parametersOf(intent.extras) }
    val imageLoadingService:ImageLoadingService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.productDetailLiveData.observe(this){
            imageLoadingService.load(binding.ivProduct,it.image)
            binding.productDetailsTitlePage.text=it.title
            binding.tvProductName.text=it.title
            binding.tvCurrentPrice.text= formatPrice(it.price)
            binding.tvPreviousPrice.text=formatPrice(it.previous_price)
            binding.tvPreviousPrice.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.ivProduct.post {
            val height=binding.ivProduct.measuredHeight
            binding.observableScroll.addScrollViewCallbacks(object :ObservableScrollViewCallbacks{
                override fun onScrollChanged(
                    scrollY: Int,
                    firstScroll: Boolean,
                    dragging: Boolean
                ) {
                    binding.toolbarCardView.alpha=scrollY.toFloat()/height.toFloat()
                    binding.ivProduct.translationY=scrollY.toFloat()/2
                }

                override fun onDownMotionEvent() {

                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

                }

            })
        }
    }
}