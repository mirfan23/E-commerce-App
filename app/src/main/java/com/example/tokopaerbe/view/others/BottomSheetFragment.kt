package com.example.tokopaerbe.view.others

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.domain.model.DataFilter
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentBottomSheetBinding
import com.example.tokopaerbe.helper.getSelectedChip
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private val viewModel: StoreViewModel by viewModel()
    private var filterListener: OnFilterFragmentListener? = null

    interface OnFilterFragmentListener {
        fun onFilterApplied(
            sort: String?, brand: String?
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        binding.apply {
            tvFilter.text = getString(R.string.filter)

            orderTitle.text = getString(R.string.sort)
            chipReview.text = getString(R.string.review_filter)
            chipSales.text = getString(R.string.sales_review)
            chipHighestPrice.text = getString(R.string.highest_price_filter)
            chipLowestPrice.text = getString(R.string.lowest_price_filter)

            categoryTitle.text = getString(R.string.category)
            chipApple.text = getString(R.string.apple_chip)
            chipAsus.text = getString(R.string.asus_chip)
            chipDell.text = getString(R.string.dell_chip)
            chipLenovo.text = getString(R.string.lenovo_chip)

            priceTitle.text = getString(R.string.price_title)
            inputLowestPrice.hint = getString(R.string.lowest)
            inputHighestPrice.hint = getString(R.string.highest)

            btnShowProduct.text = getString(R.string.btn_show_product)
            btnReset.text = getString(R.string.reset)

            chipGroupSort.isSingleSelection = true
            chipGroupCategory.isSingleSelection = true
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    private fun initListener() {
        binding.btnShowProduct.setOnClickListener {
            val selectedSort = binding.chipGroupSort.getSelectedChip()
            val selectedBrand = binding.chipGroupCategory.getSelectedChip()
            viewModel.filterProduct(
                DataFilter(
                    sort = selectedSort?.lowercase()?.trim(),
                    brand = selectedBrand?.lowercase()?.trim(),
                    lowest = binding.etLowestPrice.text.toString().toIntOrNull(),
                    highest = binding.etHighestPrice.text.toString().toIntOrNull()
                )
            )
            filterListener?.onFilterApplied(selectedSort, selectedBrand)
            dismiss()
        }
        binding.btnReset
    }

    fun setFilterListener(listener: OnFilterFragmentListener) {
        filterListener = listener
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }
}
