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
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.StoreCardViewBinding
import com.example.tokopaerbe.helper.currency

class GridViewAdapter(private val context: Context) :
    RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {

    private var gridList: List<DataProduct> = emptyList()

    fun setGridList(list: List<DataProduct>) {
        gridList = list
        notifyDataSetChanged()
    }

    inner class GridViewHolder(val binding: StoreCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gridImage: ImageView = binding.imgThumbnail
        val gridTitle: TextView = binding.tvItemName
        val gridPrice: TextView = binding.tvPrice
        val gridUser: TextView = binding.tvUploader
        val gridRating: TextView = binding.tvRatingItem
        val gridSale: TextView = binding.tvSaleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StoreCardViewBinding.inflate(inflater, parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val data = gridList[position]
        with(holder) {
            gridImage.load(data.image)
            gridTitle.text = data.name
            gridPrice.text = currency(data.price)
            gridUser.text = data.store
            gridRating.text = data.rating.toString()
            gridSale.text = context.getString(R.string.sale, data.sale.toString())

            gridTitle.ellipsize = TextUtils.TruncateAt.END
        }
    }

    override fun getItemCount(): Int {
        return gridList.size
    }
}

