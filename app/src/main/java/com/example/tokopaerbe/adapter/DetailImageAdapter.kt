package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.tokopaerbe.databinding.ImageContainerDetailBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetailImageAdapter(private val imageList: List<String>) : RecyclerView.Adapter<DetailImageAdapter.DetailViewHolder>() {
    inner class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: ImageContainerDetailBinding = ImageContainerDetailBinding.bind(itemView)

        fun bind(imageResource: String) {
            binding.apply {
                detailImage.load(imageResource)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val binding = ImageContainerDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
