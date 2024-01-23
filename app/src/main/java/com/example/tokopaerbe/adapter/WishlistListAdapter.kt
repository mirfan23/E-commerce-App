package com.example.tokopaerbe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.WishlistCardListBinding

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
    }

    override fun getItemCount(): Int {
        return listView.size
    }
}

