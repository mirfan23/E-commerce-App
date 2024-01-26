package com.example.tokopaerbe.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.R
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.databinding.WishlistCardListBinding
import com.google.android.material.button.MaterialButton

class WishlistListAdapter(
    private val listView: ArrayList<DummyGrid>,
    private val context: Context
) :
    RecyclerView.Adapter<WishlistListAdapter.WishlistListHolder>() {

    inner class WishlistListHolder(val binding: WishlistCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listImage: ImageView = binding.imgThumbnail
        val listTitle: TextView = binding.tvItemName
        val listPrice: TextView = binding.tvPrice
        val listUser: TextView = binding.tvUploader
        val btnCart: MaterialButton = binding.btnAddCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WishlistCardListBinding.inflate(inflater, parent, false)
        return WishlistListHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistListAdapter.WishlistListHolder, position: Int) {
        holder.listImage.setImageResource(listView[position].imageRes)
        holder.listTitle.text = listView[position].title
        holder.listPrice.text = listView[position].price
        holder.listUser.text = listView[position].user

        holder.listTitle.ellipsize = TextUtils.TruncateAt.END

        val button = context.getString(R.string.cart)
        holder.btnCart.text = button
    }

    override fun getItemCount(): Int {
        return listView.size
    }
}

