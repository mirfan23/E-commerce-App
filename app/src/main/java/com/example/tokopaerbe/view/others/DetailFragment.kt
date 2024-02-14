package com.example.tokopaerbe.view.others

import android.view.View
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailVariantProduct
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.oError
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
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

    private val safeArgs: DetailFragmentArgs by navArgs()
    private lateinit var adapter: DetailAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout
    private var dataCart: DataCart? = null
    private var dataWishList: DataWishList? = null

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
        binding.btnToCart.setOnClickListener {
            dataCart?.let { cart -> viewModel.insertCart(cart) }
            context?.let { context ->
                CustomSnackbar.showSnackBar(
                    context,
                    binding.root,
                    getString(R.string.successful_added_to_cart)
                )
            }
        }
        binding.cbWishlist.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dataWishList?.let { viewModel.insertWishList(it) }
                context?.let {context ->
                    CustomSnackbar.showSnackBar(
                        context,
                        binding.root,
                        getString(R.string.successful_added_to_wishlist)
                    )
                }
            }
        }
    }

    override fun observeData() {
        with(viewModel) {
            responseDetail.launchAndCollectIn(viewLifecycleOwner) { detailState ->
                detailState.onSuccess {
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    binding.apply {
                        dataCart = DataCart(
                            productId = it.productId,
                            productName = it.productName,
                            productPrice = it.productPrice,
                            image = it.image.first(),
                            stock = it.stock,
                            variant = addVariant(it.productPrice, it.productVariant).toString(),
                            quantity = 1
                        )
                        dataWishList = DataWishList(
                            productId = it.productId,
                            productName = it.productName,
                            productPrice = it. productPrice,
                            image = it.image.first(),
                            productRating = it.productRating,
                            sale = it.sale,
                        )
                        tvDetailPrice.text = currency(it.productPrice)
                        tvDetailItemName.text = it.productName
                        tvDetailDesc.text = it.description
                        tvSaleItem.text = getString(R.string.sale)
                            .replace("%sale%", it.sale.toString())
                        tvRatingItem.text = it.productRating.toString()
                        tvReviewUserPercentage.text = getString(R.string.user_satisfaction)
                            .replace("%satisfaction%", it.totalSatisfaction.toString())
                        tvTotalRatingReviewUser.text = getString(R.string.rating_review)
                            .replace("%rating%", it.totalRating.toString())
                            .replace("%review%", it.totalReview.toString())
                        chipRating.text = it.productRating.toString()
                        setProductImage(it.image)
                        for (i in it.image) {
                            when (i[0]) {
                                0.toChar() -> tabs.isGone = true
                                else -> tabs.isGone = false
                            }
                        }
                        /**
                         * nanti kode ini dipake lagi klo yang di dataCart udah bener
                         */
//                        addVariant(it.productPrice, it.productVariant)
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

    private fun addVariant(
        defaultPrice: Int,
        variants: List<DataDetailVariantProduct>
    ) {
        var totalPrice = defaultPrice
        variants.forEachIndexed { index, dataDetailVariantProduct ->
            val chip = Chip(context)
            chip.text = dataDetailVariantProduct.variantName
            chip.isCheckable = true
            chip.isChecked = index == 0
            binding.chipGroupLabelVariant.addView(chip)
            binding.chipGroupLabelVariant.isSingleSelection = true

            val variantPrice = dataDetailVariantProduct.variantPrice
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    totalPrice += variantPrice
                } else {
                    totalPrice -= variantPrice
                }
                updateTotalPrice(totalPrice)
            }
        }
    }

    private fun updateTotalPrice(totalPrice: Int) {
        binding.tvDetailPrice.text = currency(totalPrice)
    }
}
