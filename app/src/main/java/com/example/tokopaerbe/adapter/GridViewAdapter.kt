package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BasePagingAdapter
import com.example.core.domain.model.DataProduct
import com.example.tokopaerbe.R
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
            tvRatingItem.text = root.context.getString(R.string.rating_sold_sale)
                .replace("%rating%", item.productRating.toString())
                .replace("%sale%", item.sale.toString())
        }
        itemView.setOnClickListener {
            action.invoke(item)
        }
    }
}

