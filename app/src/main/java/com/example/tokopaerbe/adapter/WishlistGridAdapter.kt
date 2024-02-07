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
import com.example.tokopaerbe.databinding.WishlistCardGridBinding
import com.google.android.material.button.MaterialButton

class WishlistGridAdapter(
    private val gridView: ArrayList<DummyGrid>,
    private val context: Context
) :
    RecyclerView.Adapter<WishlistGridAdapter.WishGridViewHolder>() {

    class WishGridViewHolder(val binding: WishlistCardGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gridImage: ImageView = binding.imgThumbnail
        val gridTitle: TextView = binding.tvItemName
        val gridPrice: TextView = binding.tvPrice
        val gridUser: TextView = binding.tvUploader
        val btnCart: MaterialButton = binding.btnAddCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishGridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WishlistCardGridBinding.inflate(inflater, parent, false)
        return WishGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishGridViewHolder, position: Int) {
        holder.gridImage.setImageResource(gridView[position].imageRes)
        holder.gridTitle.text = gridView[position].title
        holder.gridPrice.text = gridView[position].price
        holder.gridUser.text = gridView[position].user

        holder.gridTitle.ellipsize = TextUtils.TruncateAt.END

        val button = context.getString(R.string.cart)
        holder.btnCart.text = button
    }

    override fun getItemCount(): Int {
        return gridView.size
    }
}