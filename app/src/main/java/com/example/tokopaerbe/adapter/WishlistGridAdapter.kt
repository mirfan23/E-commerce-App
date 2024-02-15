package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataWishList
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.WishlistCardGridBinding
import com.example.tokopaerbe.helper.currency

class WishlistGridAdapter(private val action: (DataWishList) -> Unit, private val remove: (DataWishList) -> Unit) :
    BaseListAdapter<DataWishList, WishlistCardGridBinding>(WishlistCardGridBinding::inflate) {
    override fun onItemBind(): (DataWishList, WishlistCardGridBinding, View, Int) -> Unit =
        { item, binding, itemView, _ ->
            binding.run {
                btnAddCart.text = root.context.getString(R.string.add_cart)
                imgThumbnail.load(item.image)
                tvItemName.text = item.productName
                tvPrice.text = currency(item.productPrice)
                tvUploader.text = item.store
                tvRatingItem.text = root.context.getString(R.string.rating_sold_sale)
                    .replace("%rating%", item.productRating.toString())
                    .replace("%sale%", item.sale.toString())
                btnDeleteWishlist.setOnClickListener {
                    remove.invoke(item)
                }
            }
            itemView.setOnClickListener {
                action.invoke(item)
            }
        }
}