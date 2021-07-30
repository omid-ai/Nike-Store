package com.example.nikestore.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestore.adapter.BannerSliderAdapter
import com.example.nikestore.adapter.ProductListAdapter
import com.example.nikestore.common.EXTRA_KEY_DATA
import com.example.nikestore.common.NikeFragment
import com.example.nikestore.common.convertDpToPixel
import com.example.nikestore.data.Product
import com.example.nikestore.databinding.FragmentHomeBinding
import com.example.nikestore.ui.product.ProductDetailActivity
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.ArrayList

class HomeFragment:NikeFragment(),ProductListAdapter.ProductListEventListener {

    lateinit var binding: FragmentHomeBinding
    val viewModel:HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newestProductsRv.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        binding.popularProductsRv.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)

        viewModel.progressBarLiveData.observe(this.viewLifecycleOwner){
            setProgressBar(it)
        }

        viewModel.latestProductLiveData.observe(this.viewLifecycleOwner){
            Timber.i("list of products-> $it")
            binding.newestProductsRv.adapter=ProductListAdapter(get(), it as ArrayList<Product>,this)
        }

        viewModel.popularProductLiveData.observe(this.viewLifecycleOwner){
            Timber.i("list of products-> $it")
            binding.popularProductsRv.adapter=ProductListAdapter(get(), it as ArrayList<Product>,this)
        }

        viewModel.bannerLiveData.observe(this.viewLifecycleOwner){
            Timber.i("list of banners-> $it")
            val adapter=BannerSliderAdapter(this,it)
            binding.bannerSliderViewPager.adapter=adapter
            calculateBannerSize()
            binding.sliderIndicator.setViewPager2(binding.bannerSliderViewPager)
        }
    }

    override fun onResume() {
        super.onResume()
        calculateBannerSize()
    }

    fun calculateBannerSize(){
        val height=((binding.bannerSliderViewPager.measuredWidth- convertDpToPixel(32f,requireContext()))*173)/328
        val layoutParams=binding.bannerSliderViewPager.layoutParams
        layoutParams.height=height.toInt()
        binding.bannerSliderViewPager.layoutParams=layoutParams
    }

    override fun onProductClicked(product: Product) {
        startActivity(Intent( requireContext(),ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA,product)
        })
    }
}