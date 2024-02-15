package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataWishList
import com.example.tokopaerbe.R.string.*
import com.example.tokopaerbe.databinding.WishlistCardListBinding
import com.example.tokopaerbe.helper.currency

class WishlistListAdapter(private val action: (DataWishList) -> Unit) :
    BaseListAdapter<DataWishList, WishlistCardListBinding>(WishlistCardListBinding::inflate) {
    override fun onItemBind(): (DataWishList, WishlistCardListBinding, View, Int) -> Unit =
        { item, binding, itemView, _ ->
            binding.run {
                btnAddCart.text = root.context.getString(add_cart)
                imgThumbnail.load(item.image)
                tvItemName.text = item.productName
                tvPrice.text = currency(item.productPrice)
                tvUploader.text = item.store
                    .replace("%rating%", item.productRating.toString())
                    .replace("%sale%", item.sale.toString())
            }
            itemView.setOnClickListener {
                action.invoke(item)
            }
        }
}