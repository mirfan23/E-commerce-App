package com.example.tokopaerbe.view.others

import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.databinding.FragmentCheckoutBinding
import com.example.tokopaerbe.viewmodel.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding, StoreViewModel>(FragmentCheckoutBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    override fun initView() {}

    override fun initListener() {}

    override fun observeData() {}
}