package com.example.nikestore.services

import com.example.nikestore.view.NikeImageView
import com.facebook.drawee.view.SimpleDraweeView

interface ImageLoadingService {
    fun load(imageView:NikeImageView,uri:String)
}

class FrescoImageLoadingServiceImpl():ImageLoadingService{
    override fun load(imageView: NikeImageView, uri: String) {
        if (imageView is SimpleDraweeView){
            imageView.setImageURI(uri)
        }
    }

}