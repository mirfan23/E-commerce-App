package com.example.tokopaerbe.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.core.base.BasePagingAdapter
import com.example.core.domain.model.DataProduct
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.StoreCardListViewBinding
import com.example.tokopaerbe.databinding.StoreCardViewBinding
import com.example.tokopaerbe.helper.currency

class GridViewAdapter(private val action: (DataProduct) -> Unit):
    BasePagingAdapter<DataProduct, StoreCardViewBinding>(StoreCardViewBinding::inflate) {
    override fun onItemBind(): (DataProduct, StoreCardViewBinding, View, Int) -> Unit = {
            item, binding, itemView, _ ->
        binding.run {
            imgThumbnail.load(item.image)
            tvItemName.text = item.productName
            tvPrice.text =currency(item.productPrice)
            tvUploader.text = item.store
            tvRatingItem.text = item.productRating.toString()
            tvSaleItem.text = "${R.string.sale} ${item.sale}"
        }
        itemView.setOnClickListener {
            action.invoke(item)
        }
    }
}

