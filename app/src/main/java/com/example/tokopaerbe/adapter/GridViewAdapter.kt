package com.example.tokopaerbe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.R
import com.example.tokopaerbe.data.DummyGrid

class GridViewAdapter(private val gridList: ArrayList<DummyGrid>, private val context: Context) : RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {
    class GridViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val gridTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val gridPrice: TextView = itemView.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.store_card_view, parent, false)
        return GridViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.gridTitle.text = gridList[position].gridTitle
        holder.gridPrice.text = gridList[position].gridPrice
    }

    override fun getItemCount(): Int {
        return gridList.size
    }
}