package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataCart
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.CartListCardBinding
import com.example.tokopaerbe.helper.currency

class CartAdapter(private val action: (DataCart) -> Unit, private val remove: (DataCart) -> Unit):
    BaseListAdapter<DataCart, CartListCardBinding>(CartListCardBinding::inflate) {
    override fun onItemBind(): (DataCart, CartListCardBinding, View, Int) -> Unit =
        { item, binding, itemView, position ->
            binding.run {
                imgThumbnailCart.load(item.image)
                tvItemName.text = item.productName
                tvPrice.text = currency(item.productPrice)
                tvStock.text = root.context.getString(R.string.stock).replace("%stock%", item.stock.toString())
                tvVariant.text = item.variant
                btnDeleteCart.setOnClickListener {
                    remove.invoke(item)
                }
            }
            itemView.setOnClickListener {
                action.invoke(item)
            }
        }
}