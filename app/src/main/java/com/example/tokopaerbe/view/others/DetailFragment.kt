package com.example.tokopaerbe.view.others


import android.view.View
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataDetailVariantProduct
import com.example.core.domain.state.oError
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.core.remote.data.DetailProductResponse
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.DetailAdapter
import com.example.tokopaerbe.databinding.FragmentDetailBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.currency
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class DetailFragment :
    BaseFragment<FragmentDetailBinding, StoreViewModel>(FragmentDetailBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()

    val safeArgs: DetailFragmentArgs by navArgs()
    private lateinit var adapter: DetailAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout

    override fun initView() {
        safeArgs.productId.let { productId ->
            viewModel.fetchDetail(productId)
        }
        binding.apply {
            toolbar.title = getString(R.string.detail_product)
            tvDetailVariant.text = getString(R.string.choose_variant)
            tvDetailProduct.text = getString(R.string.product_desc)
            tvDetailReview.text = getString(R.string.user_review)
            tvSeeAllReview.text = getString(R.string.see_all)
            btnBuy.text = getString(R.string.buy_now)
            btnToCart.text = getString(R.string.add_to_cart)
        }
    }

    override fun initListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.chipGroupLabelVariant.setOnCheckedStateChangeListener { group, checkedId ->
            val selectedChip = group.findViewById<Chip>(checkedId.first())
            val selectedVariantName = selectedChip.text.toString()
        }
    }

    override fun observeData() {
        with(viewModel) {
            responseDetail.launchAndCollectIn(viewLifecycleOwner) { detailState ->
                detailState.onSuccess {
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    binding.apply {
                        tvDetailPrice.text = currency(it.productPrice)
                        tvDetailItemName.text = it.productName
                        tvDetailDesc.text = it.description
                        tvSaleItem.text = "${getString(R.string.sale)} ${it.sale}"
                        tvRatingItem.text = it.productRating.toString()
                        tvReviewUserPercentage.text = getString(R.string.user_satisfaction)
                            .replace("%satisfaction%", it.totalSatisfaction.toString())
                        tvTotalRatingReviewUser.text = getString(R.string.rating_review)
                            .replace("%rating%", it.totalRating.toString())
                            .replace("%review%", it.totalReview.toString())
                        chipRating.text = it.productRating.toString()
                        if (it != null) {
                            setProductImage(it.image)
                            for (i in it.image) {
                                when (i[0]) {
                                    0.toChar() -> tabs.isGone = true
                                    else -> tabs.isGone = false
                                }
                            }
                        }
                        addVariant(it.productVariant)
                    }
                }.oError { error ->
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    val errorMessage = when (error) {
                        is HttpException -> {
                            val errorBody = error.response()?.errorBody()?.string()
                            "$errorBody"
                        }

                        else -> "${error.message}"
                    }
                    context?.let {
                        CustomSnackbar.showSnackBar(
                            it,
                            binding.root,
                            errorMessage
                        )
                    }
                }.onLoading {
                    binding.loadingOverlay.visibility = View.VISIBLE
                    binding.lottieLoading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setProductImage(imageUrls: List<String>) {
        viewPager = binding.vpDetail
        tabs = binding.tlDetail
        adapter = DetailAdapter(imageUrls)
        viewPager.adapter = adapter

        TabLayoutMediator(tabs, viewPager) { _, _ -> }.attach()
    }

    private fun addVariant(variants: List<DataDetailVariantProduct>) {
        for (variant in variants) {
            val chip = Chip(context)
            chip.text = variant.variantName
            binding.chipGroupLabelVariant.addView(chip)
        }
    }
}