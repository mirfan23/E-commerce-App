package com.example.tokopaerbe.view.dashboard

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataProduct
import com.example.core.domain.state.oError
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.GridViewAdapter
import com.example.tokopaerbe.adapter.ListViewAdapter
import com.example.core.remote.data.DummyGrid
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.databinding.FragmentStoreBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.helper.visibleIf
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class StoreFragment :
    BaseFragment<FragmentStoreBinding, StoreViewModel>(FragmentStoreBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridViewAdapter: GridViewAdapter
    private lateinit var listViewAdapter: ListViewAdapter
    private val gridList: ArrayList<DataProduct> = arrayListOf()
    private lateinit var buttonView: Chip
    private lateinit var buttonFilter: Chip

    override fun observeData() {
        with(viewModel) {
            responseProduct.launchAndCollectIn(viewLifecycleOwner) { productState ->
                productState.onSuccess { data ->
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    gridList.addAll(data)
                    gridViewAdapter.notifyDataSetChanged()
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
        val layoutManager = GridLayoutManager(context, 2)
        gridViewAdapter = GridViewAdapter(requireContext())
        listViewAdapter = ListViewAdapter(requireContext())
        binding.btnFilter.text = getString(R.string.filter)
        binding.searchBar.hint = getString(R.string.search)

        recyclerView.adapter = gridViewAdapter

        gridViewAdapter.setGridList(gridList)
        listViewAdapter.setList(gridList)

        recyclerView = binding.rvGridItem
        buttonView = binding.btnChangeView
        buttonFilter = binding.btnFilter
        recyclerView.layoutManager = layoutManager

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
        recyclerView.addItemDecoration(SpaceItemDecoration(spaceInPixels))

        gridViewAdapter.notifyDataSetChanged()
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
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gridViewAdapter
        }
        buttonView.isChecked = false
    }

    private fun switchToListView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listViewAdapter
        }
        buttonView.isChecked = true
    }
}
