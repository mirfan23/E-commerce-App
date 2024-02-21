package com.example.tokopaerbe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.core.base.BaseListAdapter
import com.example.core.domain.model.DataPayment
import com.example.core.domain.model.DataPaymentItem
import com.example.tokopaerbe.databinding.PaymentListBinding
import com.example.tokopaerbe.databinding.PaymentListItemBinding
import com.example.tokopaerbe.helper.visibleIf

class PaymentItemAdapter(
    private val list: List<DataPaymentItem>,
) : RecyclerView.Adapter<PaymentItemAdapter.PaymentItemViewHolder>() {

    inner class PaymentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: PaymentListItemBinding = PaymentListItemBinding.bind(itemView)

        fun bind(data: DataPaymentItem) {
            binding.apply {
//                ivIcon.visibleIf(data.label.equals("OVO", true).not() && data.label.equals("GoPay", true).not())
                ivIcon.load(data.image)
//                sivIcon.visibleIf(data.label.equals("OVO", true) || data.label.equals("GoPay", true))
//                sivIcon.load(data.image)

                tvName.text = data.label
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentItemAdapter.PaymentItemViewHolder {
        val binding = PaymentListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PaymentItemAdapter.PaymentItemViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}