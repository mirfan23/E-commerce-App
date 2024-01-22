package com.example.tokopaerbe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.R
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.StoreCardViewBinding
import com.example.tokopaerbe.databinding.WishlistCardGridBinding

class WishlistGridAdapter(
    private val gridView: ArrayList<DummyGrid>,
    private val context: Context
) :
    RecyclerView.Adapter<WishlistGridAdapter.WishGridViewHolder>() {

    class WishGridViewHolder(val binding: WishlistCardGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gridTitle: TextView = binding.tvItemName
        val gridPrice: TextView = binding.tvPrice
        val gridUser: TextView = binding.tvUploader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishGridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WishlistCardGridBinding.inflate(inflater, parent, false)
        return WishGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistGridAdapter.WishGridViewHolder, position: Int) {
        holder.gridTitle.text = gridView[position].gridTitle
        holder.gridPrice.text = gridView[position].gridPrice
        holder.gridUser.text = gridView[position].gridUser
    }

    override fun getItemCount(): Int {
        return gridView.size
    }
}