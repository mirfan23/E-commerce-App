package com.example.tokopaerbe.view.dashboard

import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.databinding.FragmentTransactionBinding
import com.example.tokopaerbe.viewmodel.TransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionFragment : BaseFragment<FragmentTransactionBinding, TransactionViewModel>(FragmentTransactionBinding::inflate) {
    override val viewModel: TransactionViewModel by viewModel()
    override fun initView() {}

    override fun initListener() {}

    override fun observeData() {}


}