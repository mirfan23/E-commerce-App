package com.example.tokopaerbe.view.dashboard

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.WishlistGridAdapter
import com.example.tokopaerbe.adapter.WishlistListAdapter
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.databinding.FragmentWishlistBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishlistFragment : BaseFragment<FragmentWishlistBinding, PreLoginViewModel>(FragmentWishlistBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var wishlistListAdapter: WishlistListAdapter
    private lateinit var wishlistGridAdapter: WishlistGridAdapter
    private lateinit var listView: ArrayList<DummyGrid>
    private lateinit var gridList: ArrayList<DummyGrid>
    private lateinit var button: Chip

    override fun observeData() {}

    override fun initView() {
        recyclerView = binding.rvListView
        button = binding.btnChangeView
        listView = ArrayList()
        gridList = ArrayList()

        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager

        wishlistListAdapter = WishlistListAdapter(listView, requireContext())
        wishlistGridAdapter = WishlistGridAdapter(gridList, requireContext())

        recyclerView.adapter = wishlistListAdapter

        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))
        listView.add(DummyGrid(R.drawable.thumbnail_store, getString(R.string.item_name), "Rp. 20.000.000", "User"))

        gridList.addAll(listView)

        wishlistListAdapter.notifyDataSetChanged()

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)

        recyclerView.addItemDecoration(SpaceItemDecoration(spaceInPixels))

        binding.tvAmount.text = getString(R.string.items)
    }

    override fun initListener() {
        button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchToGridView()
            } else {
                switchToListView()
            }
        }
    }

    private fun switchToGridView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = wishlistGridAdapter
        }
        button.isChecked = true
    }

    private fun switchToListView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wishlistListAdapter
        }

        button.isChecked= false
    }
}