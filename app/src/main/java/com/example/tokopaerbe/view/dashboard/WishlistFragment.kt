package com.example.tokopaerbe.view.dashboard

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailVariantProduct
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.onSuccess
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.WishlistGridAdapter
import com.example.tokopaerbe.adapter.WishlistListAdapter
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.databinding.FragmentWishlistBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.viewmodel.StoreViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishlistFragment :
    BaseFragment<FragmentWishlistBinding, StoreViewModel>(FragmentWishlistBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    private var dataWishList: List<DataWishList>? = null
    private val listVariant: ArrayList<DataDetailVariantProduct> = arrayListOf()
    private val wishlistAdapter by lazy {
        WishlistListAdapter(
            action = {
                val bundle = bundleOf("productId" to it.productId)
                activity?.supportFragmentManager?.findFragmentById(R.id.fragment_container)
                    ?.findNavController()
                    ?.navigate(R.id.action_dashboardFragment_to_detailFragment, bundle)
            },
            remove = { removeItemFromWishlist(it)
            }
        )
    }
    private val wishlistAdapterGrid by lazy {
        WishlistGridAdapter(
            action = {
                val bundle = bundleOf("productId" to it.productId)
                activity?.supportFragmentManager?.findFragmentById(R.id.fragment_container)
                    ?.findNavController()
                    ?.navigate(R.id.action_dashboardFragment_to_detailFragment, bundle)
            },
            remove = { removeItemFromWishlist(it) }
        )
    }

    override fun observeData() {
        with(viewModel) {
            fetchWishList().launchAndCollectIn(viewLifecycleOwner) { wishListState ->
                this.launch {
                    wishListState.onSuccess { data ->
                        dataWishList = data
                        wishlistAdapter.submitList(data)
                    }
                }
            }
        }
    }

    private fun observeDataGrid() {
        with(viewModel) {
            fetchWishList().launchAndCollectIn(viewLifecycleOwner) { wishListState ->
                this.launch {
                    wishListState.onSuccess { data ->
                        dataWishList = data
                        wishlistAdapterGrid.submitList(data)
                    }
                }
            }
        }
    }

    override fun initView() {
        switchToListView()

        viewModel.fetchWishList()

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.rvListView.addItemDecoration(SpaceItemDecoration(spaceInPixels))
        binding.tvAmount.text = getString(R.string.items)
    }

    override fun initListener() {
        binding.btnChangeView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.btnChangeView.chipIcon =
                    context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_list) }
                switchToGridView()
            } else {
                binding.btnChangeView.chipIcon =
                    context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_grid) }
                switchToListView()
            }
        }
    }

    private fun switchToGridView() {
        observeDataGrid()
        binding.rvListView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = wishlistAdapterGrid
        }
        binding.btnChangeView.isChecked = true
    }

    private fun switchToListView() {
        binding.rvListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wishlistAdapter
        }

        binding.btnChangeView.isChecked = false
    }

    private fun removeItemFromWishlist(dataWishList: DataWishList) {
        viewModel.removeWishlistWishlist(dataWishList)
    }
}