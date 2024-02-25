package com.example.tokopaerbe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.DataPayment
import com.example.core.domain.model.DataPaymentItem
import com.example.tokopaerbe.databinding.PaymentListBinding

class PaymentAdapter(private val list: List<DataPayment>, ) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    inner class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: PaymentListBinding = PaymentListBinding.bind(itemView)

        fun bind(data: DataPayment) {
            binding.apply {
                tvTitle.text = data.title
                rvItemPayment.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = PaymentItemAdapter(data.item)
                    println("MASUK: $data")
                    setHasFixedSize(true)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentAdapter.PaymentViewHolder {
        val binding = PaymentListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PaymentAdapter.PaymentViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}