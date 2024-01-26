package com.example.tokopaerbe.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.WishlistGridAdapter
import com.example.tokopaerbe.adapter.WishlistListAdapter
import com.example.core.remote.data.DummyGrid
import com.example.tokopaerbe.databinding.FragmentWishlistBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.google.android.material.chip.Chip

class WishlistFragment : Fragment() {
    private lateinit var binding: FragmentWishlistBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var wishlistListAdapter: WishlistListAdapter
    private lateinit var wishlistGridAdapter: WishlistGridAdapter
    private lateinit var listView: ArrayList<com.example.core.remote.data.DummyGrid>
    private lateinit var gridList: ArrayList<com.example.core.remote.data.DummyGrid>
    private lateinit var button: Chip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchToGridView()
            } else {
                switchToListView()
            }
        }
        binding.tvAmount.text = getString(R.string.items)
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