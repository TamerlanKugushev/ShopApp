package com.example.shopapp.presentation.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import com.example.shopapp.R
import com.example.shopapp.data.imageloader.ImageLoader
import com.example.shopapp.data.model.local.BestSeller
import com.example.shopapp.data.model.local.HomeStore
import com.example.shopapp.databinding.ItemBestSellerBinding
import com.example.shopapp.databinding.ItemHotSalesBinding
import com.example.shopapp.presentation.adapter.listeners.BaseItemListener
import com.example.shopapp.presentation.adapter.listeners.BestSellerItemListener
import com.example.shopapp.utils.mapIntToPriceForBestSeller

fun bindHotSales(
    view: View,
    data: HomeStore,
    listener: BaseItemListener,
    imageLoader: ImageLoader
) {
    val binding = ItemHotSalesBinding.bind(view)
    val urlPicture = data.picture
    val title = data.title
    val subtitle = data.subtitle
    val isNew = data.isNew ?: false
    with(binding) {
        urlPicture?.let {
            imageLoader.loadImage(it, ivPhoneHotSales)
        }
        tvPhoneName.text = title
        tvPhoneDescription.text = subtitle
        if (!isNew) ivNew.visibility = View.INVISIBLE
    }

}

fun bindBestSeller(
    view: View,
    data: BestSeller,
    listener: BestSellerItemListener,
    imageLoader: ImageLoader
) {
    val binding: ItemBestSellerBinding = ItemBestSellerBinding.bind(view)
    val discountPrise = mapIntToPriceForBestSeller(data.discountPrice ?: 0)
    val priceWithoutDiscount = mapIntToPriceForBestSeller(data.priceWithoutDiscount ?: 0)
    val uri = data.picture
    val isFavorites = data.isFavorites ?: false
    val title = data.title

    val span = SpannableString(priceWithoutDiscount)
    span.setSpan(StrikethroughSpan(), 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    view.setOnClickListener {
        listener.itemListener(data.id.toString())
    }

    with(binding) {
        uri?.let { imageLoader.loadImage(it, ivPhoneBestSeller) }
        tvModelPhone.text = title
        tvDiscountPrice.text = discountPrise
        tvPriceWithoutDiscount.text = span
        if (isFavorites) ivLikeBestSeller.setImageResource(R.drawable.like)
    }
}