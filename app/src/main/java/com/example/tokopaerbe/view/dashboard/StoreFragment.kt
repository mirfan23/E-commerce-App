package com.example.tokopaerbe.view.dashboard

import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataProduct
import com.example.core.domain.state.oError
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.GridViewAdapter
import com.example.tokopaerbe.adapter.ListViewAdapter
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.databinding.FragmentStoreBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.helper.visibleIf
import com.example.tokopaerbe.view.others.BottomSheetFragment
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class StoreFragment :
    BaseFragment<FragmentStoreBinding, StoreViewModel>(FragmentStoreBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    private val listAdapter by lazy {
        ListViewAdapter {data ->
            Toast.makeText(context, data.productName, Toast.LENGTH_LONG).show()
        }
    }
    private val gridAdapter by lazy {
        GridViewAdapter {data ->
            Toast.makeText(context, data.productName, Toast.LENGTH_LONG).show()
        }
    }
    private lateinit var buttonView: Chip
    private lateinit var buttonFilter: Chip
    private var pagingData : PagingData<DataProduct>? = null

    override fun observeData() {
        with(viewModel) {
            fetchProduct().launchAndCollectIn(viewLifecycleOwner){productState ->
                this.launch {
                    productState.onSuccess { data ->
                        binding.loadingOverlay.visibility = View.GONE
                        binding.lottieLoading.visibility = View.GONE
                        pagingData = data
                        gridAdapter.submitData(viewLifecycleOwner.lifecycle, data)
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
    }

    fun fetchList() {
        viewModel.fetchProduct().launchAndCollectIn(viewLifecycleOwner){productState ->
            this.launch {
                productState.onSuccess { data ->
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    pagingData = data
                    listAdapter.submitData(viewLifecycleOwner.lifecycle, data)
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

    override fun initView() {

//        recyclerView = binding.rvGridItem
        switchToGridView()
        binding.btnFilter.text = getString(R.string.filter)
        binding.searchBar.hint = getString(R.string.search)

//        recyclerView.adapter = gridAdapter


        buttonView = binding.btnChangeView
        buttonFilter = binding.btnFilter
//        recyclerView.layoutManager = layoutManager

        viewModel.fetchProduct()

        if (true) {
            binding.errorView.visibleIf(false)
            binding.errorView.setErrorMessage(
                title = "Error",
                description = "error aja ini",
                action = {}
            )
        }

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.rvGridItem.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }

    override fun initListener() {
        buttonView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.btnChangeView.chipIcon =
                    context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_grid) }
                switchToListView()
            } else {
                binding.btnChangeView.chipIcon =
                    context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_list) }
                switchToGridView()
            }
        }
        buttonFilter.setOnClickListener {
            val modal = BottomSheetFragment()
            childFragmentManager.let { modal.show(it, BottomSheetFragment.TAG) }
        }
    }

    private fun switchToGridView() {
        binding.rvGridItem.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gridAdapter
        }
        binding.btnChangeView.isChecked = false
    }

    private fun switchToListView() {
        fetchList()
        binding.rvGridItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        binding.btnChangeView.isChecked = true
    }
}
