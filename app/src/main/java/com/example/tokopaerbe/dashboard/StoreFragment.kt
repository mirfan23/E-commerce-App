package com.example.tokopaerbe.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.GridViewAdapter
import com.example.tokopaerbe.data.DummyGrid
import com.example.tokopaerbe.databinding.FragmentStoreBinding
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.helper.visibleIf

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding
    lateinit var gridRV: RecyclerView
    lateinit var gridAdapter: GridViewAdapter
    lateinit var gridList: ArrayList<DummyGrid>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (true) {
            binding.errorView.visibleIf(false)
            binding.errorView.setErrorMessage(
                title = "Error",
                description = "error aja ini",
                action = {}
            )
        }

        gridRV = binding.rvGridItem
        gridList = ArrayList()

        val layoutManager = GridLayoutManager(context, 2)

        gridRV.layoutManager = layoutManager

        gridAdapter = GridViewAdapter(gridList, requireContext())

        gridRV.adapter = gridAdapter

        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))
        gridList.add(DummyGrid("Laptop Baru", "Rp. 20.000.000"))

        gridAdapter.notifyDataSetChanged()

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)

        gridRV.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }
}