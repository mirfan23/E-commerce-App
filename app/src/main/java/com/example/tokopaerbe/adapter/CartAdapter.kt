package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataCart
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.CartListCardBinding
import com.example.tokopaerbe.helper.currency
import com.example.tokopaerbe.viewmodel.StoreViewModel

class CartAdapter(
    private val action: (DataCart) -> Unit,
    private val remove: (DataCart) -> Unit,
    private val add: (Int, Int) -> Unit,
    private val min: (Int, Int) -> Unit,
    private val checkbox: (Int, Boolean) -> Unit
) :
    BaseListAdapter<DataCart, CartListCardBinding>(CartListCardBinding::inflate) {
    override fun onItemBind(): (DataCart, CartListCardBinding, View, Int) -> Unit =
        { item, binding, itemView, _ ->
            binding.run {
                imgThumbnailCart.load(item.image)
                tvItemName.text = item.productName
                tvPrice.text = currency(item.productPrice)
                tvStock.text =
                    root.context.getString(R.string.stock).replace("%stock%", item.stock.toString())
                tvVariant.text = item.variant
                btnDeleteCart.setOnClickListener {
                    remove.invoke(item)
                }
                btnAddCart.setOnClickListener {
                    if (item.quantity < item.stock) {
                        add.invoke(item.cartId, item.quantity)
                    }
                }
                btnQuantity.text = item.quantity.toString()
                btnDecrementCart.setOnClickListener {
                    if (item.quantity > 1) {
                        min.invoke(item.cartId, item.quantity)
                    } else {
                        remove.invoke(item)
                    }
                }
                cbCart.setOnCheckedChangeListener { _, isChecked ->
                    checkbox(item.cartId, isChecked)
                }
            }
            itemView.setOnClickListener {
                action.invoke(item)
            }
        }

}