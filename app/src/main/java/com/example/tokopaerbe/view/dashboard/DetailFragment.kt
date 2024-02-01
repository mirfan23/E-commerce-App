package com.example.tokopaerbe.view.dashboard


import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.databinding.FragmentDetailBinding
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, PreLoginViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()
    override fun initView() {}

    override fun initListener() {}

    override fun observeData() {}
}