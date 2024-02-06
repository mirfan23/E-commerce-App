package com.example.tokopaerbe.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.core.domain.model.DataProduct
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.StoreCardListViewBinding
import com.example.tokopaerbe.helper.currency

class ListViewAdapter(private val context: Context) :
    RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {

    private var listViewData: List<DataProduct> = emptyList()

    fun setList(list: List<DataProduct>) {
        listViewData = list
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val binding: StoreCardListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listImage: ImageView = binding.imgThumbnail
        val listTitle: TextView = binding.tvItemName
        val listPrice: TextView = binding.tvPrice
        val listUser: TextView = binding.tvUploader
        val listRatingItem: TextView = binding.tvRatingItem
        val listSaleItem: TextView = binding.tvSaleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoreCardListViewBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listViewData[position]
        with(holder) {
            listImage.load(data.image)
            listTitle.text = data.name
            listPrice.text = currency(data.price)
            listUser.text = data.store
            listRatingItem.text = data.rating.toString()
            listSaleItem.text = context.getString(R.string.sale, data.sale.toString())

            listTitle.ellipsize = TextUtils.TruncateAt.END
        }
    }

    override fun getItemCount(): Int {
        return listViewData.size
    }
}

