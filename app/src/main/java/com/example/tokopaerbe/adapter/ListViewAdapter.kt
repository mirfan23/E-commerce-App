package com.example.tokopaerbe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.StoreCardListViewBinding

class ListViewAdapter(private val listView: ArrayList<DummyGrid>, private val context: Context) :
    RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {
    inner class ListViewHolder(val binding: StoreCardListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listTitle: TextView = binding.tvItemName
        val listPrice: TextView = binding.tvPrice
        val listUser: TextView = binding.tvUploader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoreCardListViewBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.listTitle.text = listView[position].gridTitle
        holder.listPrice.text = listView[position].gridPrice
        holder.listUser.text = listView[position].gridUser
    }

    override fun getItemCount(): Int {
        return listView.size
    }
}

