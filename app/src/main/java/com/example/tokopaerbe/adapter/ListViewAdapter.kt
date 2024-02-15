package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BasePagingAdapter
import com.example.core.domain.model.DataProduct
import com.example.tokopaerbe.R
import com.example.tokopaerbe.R.string.*
import com.example.tokopaerbe.databinding.StoreCardListViewBinding
import com.example.tokopaerbe.helper.currency

class ListViewAdapter(private val action: (DataProduct) -> Unit):
        BasePagingAdapter<DataProduct, StoreCardListViewBinding>(StoreCardListViewBinding::inflate) {
    override fun onItemBind(): (DataProduct, StoreCardListViewBinding, View, Int) -> Unit = {
        item, binding, itemView, _ ->
        binding.run {
            imgThumbnail.load(item.image)
            tvItemName.text = item.productName
            tvPrice.text =currency(item.productPrice)
            tvUploader.text = item.store
            tvRatingItem.text = root.context.getString(rating_sold_sale)
                .replace("%rating%", item.productRating.toString())
                .replace("%sale%", item.sale.toString())
        }
        itemView.setOnClickListener {
            action.invoke(item)
        }
    }
}