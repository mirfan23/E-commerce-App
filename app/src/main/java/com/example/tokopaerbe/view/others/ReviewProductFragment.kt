package com.example.tokopaerbe.view.others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.core.base.BaseFragment
import com.example.core.domain.state.oError
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.ReviewAdapter
import com.example.tokopaerbe.databinding.FragmentReviewProductBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.viewmodel.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewProductFragment : BaseFragment<FragmentReviewProductBinding, StoreViewModel>(FragmentReviewProductBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()

    private val safeArgs: ReviewProductFragmentArgs by navArgs()
    private lateinit var rvReview: RecyclerView
    private val reviewAdapter = ReviewAdapter()
    override fun initView() {
        rvReview = binding.rvReview
        rvReview.setHasFixedSize(true)
        binding.reviewToolbar.title = getString(R.string.review_title)
        safeArgs.productId.let { productId ->
            viewModel.fetchReviewProducts(productId)
        }
        listReview()
        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.rvReview.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }

    override fun initListener() {
        binding.apply {
            reviewToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun observeData() {
        with(viewModel) {
            responseReview.launchAndCollectIn(viewLifecycleOwner){state ->
                state.onLoading {}
                    .oError {}
                    .onSuccess {
                        reviewAdapter.submitList(it)
                    }
            }
        }
    }

    private fun listReview() {
        rvReview.run {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }
    }
}