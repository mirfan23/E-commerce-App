package com.example.tokopaerbe.view.others


import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentDetailBinding
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, PreLoginViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()
    override fun initView() {
        binding.apply {
            toolbar.title = getString(R.string.detail_product)
            tvDetailPrice.text = getString(R.string.product_price)
            tvDetailItemName.text = getString(R.string.item_name)
            tvDetailVariant.text = getString(R.string.choose_variant)
            tvDetailProduct.text = getString(R.string.product_desc)
            tvDetailReview.text = getString(R.string.user_review)
            tvSeeAllReview.text = getString(R.string.see_all)
        }
    }

    override fun initListener() {}

    override fun observeData() {}
}