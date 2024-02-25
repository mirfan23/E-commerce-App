package com.example.tokopaerbe.view.others

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.oError
import com.example.core.domain.state.onCreated
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.core.domain.state.onValue
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.CartAdapter
import com.example.tokopaerbe.databinding.FragmentCartBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SpaceItemDecoration
import com.example.tokopaerbe.helper.currency
import com.example.tokopaerbe.helper.visibleIf
import com.example.tokopaerbe.viewmodel.StoreViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class CartFragment :
    BaseFragment<FragmentCartBinding, StoreViewModel>(FragmentCartBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()

    private val checkedItems = mutableListOf<Int>()
    private val cartAdapter by lazy {
        CartAdapter(
            action = {
                val bundle = bundleOf("productId" to it.productId)
                findNavController().navigate(R.id.action_cartFragment_to_detailFragment, bundle)
            },
            remove = { entity -> removeItemFromCart(entity) },
            add = { id, quantity ->
                viewModel.updateQuantity(id, quantity + 1)
                updateTotalPrice()
            },
            min = { id, quantity ->
                viewModel.updateQuantity(id, quantity - 1)
                updateTotalPrice()
            },
            checkbox = { id, isChecked ->
                if (isChecked) {
                    viewModel.updateCheckCart(id, isChecked)
                    checkedItems.add(id)
                } else {
                    checkedItems.remove(id)
                }
                updateTotalPrice()
            }
        )
    }

    override fun observeData() {
        with(viewModel) {
            fetchCart().launchAndCollectIn(viewLifecycleOwner) { cartState ->
                this.launch {
                    cartState.onLoading {}.oError { error ->
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
                    }.onSuccess { data ->
                        cartAdapter.submitList(data)
                        viewModel.setDataListCart(data)
                        /**
                         * nanti dipake lagi
                         */
//                        if (data.isEmpty()) {
//                            binding.errorView.visibleIf(true)
//                            binding.errorView.setErrorMessage(
//                                title = "Empty",
//                                description = "Cart is Empty",
//                                action = {
//                                    findNavController().navigate(R.id.action_wishlistFragment_to_storeFragment)
//                                }
//                            )
//                        }
                    }

                }
            }
            totalPriceCart.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { totalPriceCart(it) }
            }
        }
    }

    override fun initView() {
        listView()
        viewModel.fetchCart()

        binding.apply {
            cartToolbar.title = getString(R.string.cart_title)
            tvSelectAllCart.text = getString(R.string.select_all)
            btnDeleteCart.text = getString(R.string.delete)
            tvTotalPaymentCart.text = getString(R.string.total_payment)
            btnPay.text = getString(R.string.buy)
        }
        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        binding.rvListView.addItemDecoration(SpaceItemDecoration(spaceInPixels))
    }

    override fun initListener() {
        binding.cartToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
        binding.cbSelectAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

            } else {
                checkedItems.clear()
            }
            updateTotalPrice()
        }
    }

    private fun listView() {
        binding.rvListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }

    private fun removeItemFromCart(dataCart: DataCart) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.are_you_sure))
                .setMessage(getString(R.string.item_will_deleted))
                .setNegativeButton(getString(R.string.cancel_btn_cart)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.delete_btn_cart)) { _, _ ->
                    removeCartItem(dataCart)
                }
                .show()
                .getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(it, R.color.error))
        }
    }

    private fun updateTotalPrice() {
        viewModel.updateTotalPriceCart(checkedItems)
    }

    private fun totalPriceCart(totalPrice: Int) {
        binding.tvTotalPrice.text = currency(totalPrice)
    }

    private fun removeCartItem(dataCart: DataCart) {
        viewModel.removeCart(dataCart)
    }
}