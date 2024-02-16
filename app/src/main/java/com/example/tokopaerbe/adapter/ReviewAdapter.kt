package com.example.tokopaerbe.adapter

import android.view.View
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataReviewProduct
import com.example.tokopaerbe.databinding.ListReviewCardBinding

class ReviewAdapter :
    BaseListAdapter<DataReviewProduct, ListReviewCardBinding>(ListReviewCardBinding::inflate) {
    override fun onItemBind(): (DataReviewProduct, ListReviewCardBinding, View, Int) -> Unit =
        { item, binding, _, _ ->
            binding.run {
                ivUser.load(item.userImage)
                tvReviewUserName.text = item.userReview
                tvReview.text = item.userReview
                rbReview.rating = item.userRating.toFloat()
            }
        }
}