package com.example.tokopaerbe.view.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentReviewProductBinding
import com.example.tokopaerbe.viewmodel.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewProductFragment : BaseFragment<FragmentReviewProductBinding, StoreViewModel>(FragmentReviewProductBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    override fun initView() {}

    override fun initListener() {}

    override fun observeData() {}
}