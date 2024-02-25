package com.example.tokopaerbe.view.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataPayment
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.PaymentAdapter
import com.example.tokopaerbe.databinding.FragmentPaymentBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentFragment :
    BaseFragment<FragmentPaymentBinding, StoreViewModel>(FragmentPaymentBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    private val list: MutableList<DataPayment> = mutableListOf()
    private lateinit var paymentAdapter: PaymentAdapter

    override fun initView() {
        binding.apply {
            paymentAdapter = PaymentAdapter(list)
            toolbarPayment.title = "Payment"
            rvItemPayment.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = paymentAdapter
            }
        }
    }

    override fun initListener() {
        binding.toolbarPayment.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeData() {
        with(viewModel) {
            getDataPayment()
            getConfigStatusUpdate().launchAndCollectIn(viewLifecycleOwner) {
                getDataPayment()
            }
        }
    }

    fun getDataPayment() {
        viewModel.doPayment().launchAndCollectIn(viewLifecycleOwner) {
            println("MASUK FRAGMENT : $it")
            list.addAll(it)
            paymentAdapter.notifyDataSetChanged()
        }
    }
}