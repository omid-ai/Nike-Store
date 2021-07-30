package com.example.nikestore.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikestore.R
import com.example.nikestore.common.formatPrice
import com.example.nikestore.common.implementSpringAnimationTrait
import com.example.nikestore.data.Product
import com.example.nikestore.services.ImageLoadingService
import com.example.nikestore.view.NikeImageView
import java.util.*

class ProductListAdapter(
    val imageLoadingService: ImageLoadingService,
    val products: ArrayList<Product>,
    val eventListener: ProductListEventListener
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productIv = itemView.findViewById<NikeImageView>(R.id.productIv)
        val productName = itemView.findViewById<TextView>(R.id.productNameTv)
        val currentPrice = itemView.findViewById<TextView>(R.id.currentPriceTv)
        val previousPrice = itemView.findViewById<TextView>(R.id.previousPriceTv)
        val favoriteBtn = itemView.findViewById<ImageView>(R.id.favoriteBtn)

        fun bindProduct(product: Product) {
            imageLoadingService.load(productIv, product.image)
            productName.text = product.title
            currentPrice.text = formatPrice(product.price)
            previousPrice.text = formatPrice(product.previous_price)
            previousPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.implementSpringAnimationTrait()
            itemView.setOnClickListener {
                eventListener.onProductClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    override fun getItemCount(): Int = products.size

    interface ProductListEventListener {
        fun onProductClicked(product: Product)
    }
}