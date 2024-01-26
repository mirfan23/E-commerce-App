package com.example.tokopaerbe.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.databinding.StoreCardListViewBinding

class ListViewAdapter(private val listView: ArrayList<DummyGrid>, private val context: Context) :
    RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {
    inner class ListViewHolder(val binding: StoreCardListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listImage: ImageView = binding.imgThumbnail
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
        holder.listImage.setImageResource(listView[position].imageRes)
        holder.listTitle.text = listView[position].title
        holder.listPrice.text = listView[position].price
        holder.listUser.text = listView[position].user

        holder.listTitle.ellipsize = TextUtils.TruncateAt.END
    }

    override fun getItemCount(): Int {
        return listView.size
    }
}

