package com.example.tokopaerbe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.StoreCardViewBinding

class GridViewAdapter(private val gridList: ArrayList<DummyGrid>, private val context: Context) :
    RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {
    inner class GridViewHolder(val binding: StoreCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
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
        holder.gridTitle.text = gridList[position].gridTitle
        holder.gridPrice.text = gridList[position].gridPrice
        holder.gridUser.text = gridList[position].gridUser
    }

    override fun getItemCount(): Int {
        return gridList.size
    }
}

