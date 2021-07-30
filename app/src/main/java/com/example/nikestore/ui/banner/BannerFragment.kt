package com.example.nikestore.ui.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nikestore.common.EXTRA_KEY_DATA
import com.example.nikestore.data.Banner
import com.example.nikestore.databinding.FragmentBannerBinding
import com.example.nikestore.services.ImageLoadingService
import org.koin.android.ext.android.inject

class BannerFragment:Fragment() {

    lateinit var binding: FragmentBannerBinding
    val imageLoadingService:ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBannerBinding.inflate(layoutInflater,container,false)
        val imageView=binding.root
        val banner=requireArguments().getParcelable<Banner>(EXTRA_KEY_DATA)?:throw IllegalStateException("uri can not be null")
        imageLoadingService.load(imageView,banner.image)
        return imageView
    }

    companion object{
        fun newInstance(banner: Banner):BannerFragment{
            return BannerFragment().apply {
                arguments=Bundle().apply {
                    putParcelable(EXTRA_KEY_DATA,banner)
                }
            }
        }
    }
}