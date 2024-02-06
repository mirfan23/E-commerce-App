package com.example.tokopaerbe.view.dashboard

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.GridViewAdapter
import com.example.tokopaerbe.adapter.ListViewAdapter
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.databinding.FragmentStoreBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.helper.visibleIf
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<FragmentStoreBinding, PreLoginViewModel>(FragmentStoreBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridViewAdapter: GridViewAdapter
    private lateinit var listViewAdapter: ListViewAdapter
    private lateinit var gridList: ArrayList<DummyGrid>
    private lateinit var listView: ArrayList<DummyGrid>
    private lateinit var buttonView: Chip
    private lateinit var buttonFilter: Chip

    override fun observeData() {}

    override fun initView() {
        binding.btnFilter.text = getString(R.string.filter)
        binding.searchBar.hint = getString(R.string.search)

        if (true) {
            binding.errorView.visibleIf(false)
            binding.errorView.setErrorMessage(
                title = "Error",
                description = "error aja ini",
                action = {}
            )
        }

        recyclerView = binding.rvGridItem
        buttonView = binding.btnChangeView
        buttonFilter = binding.btnFilter
        gridList = ArrayList()
        listView = ArrayList()

        val layoutManager = GridLayoutManager(context, 2)

        recyclerView.layoutManager = layoutManager

        gridViewAdapter = GridViewAdapter(gridList, requireContext())
        listViewAdapter = ListViewAdapter(listView, requireContext())

        recyclerView.adapter = gridViewAdapter

        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))
        gridList.add(DummyGrid(R.drawable.thumbnail_store,getString(R.string.item_name), "Rp. 20.000.000", "User"))

        listView.addAll(gridList)

        gridViewAdapter.notifyDataSetChanged()

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)

        recyclerView.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }

    override fun initListener() {
        buttonView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchToListView()
            } else {
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