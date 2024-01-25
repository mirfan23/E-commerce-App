package com.example.tokopaerbe.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.StoreCardViewBinding

class GridViewAdapter(private val gridList: ArrayList<DummyGrid>, private val context: Context) :
    RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {
    inner class GridViewHolder(val binding: StoreCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val gridImage: ImageView = binding.imgThumbnail
        val gridTitle: TextView = binding.tvItemName
        val gridPrice: TextView = binding.tvPrice
        val gridUser: TextView = binding.tvUploader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoreCardViewBinding.inflate(inflater, parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.gridImage.setImageResource(gridList[position].imageRes)
        holder.gridTitle.text = gridList[position].title
        holder.gridPrice.text = gridList[position].price
        holder.gridUser.text = gridList[position].user

        holder.gridTitle.ellipsize = TextUtils.TruncateAt.END
    }

    override fun getItemCount(): Int {
        return gridList.size
    }
}

