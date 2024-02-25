package com.example.tokopaerbe.view.others

import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentCheckoutBinding
import com.example.tokopaerbe.viewmodel.StoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment :
    BaseFragment<FragmentCheckoutBinding, StoreViewModel>(FragmentCheckoutBinding::inflate) {
    override val viewModel: StoreViewModel by viewModel()
    override fun initView() {
        binding.apply {
            checkoutToolbar.title = getString(R.string.checkout_title)
            tvPayment.text = getString(R.string.payment)
            tvTotalPaymentCheckout.text = getString(R.string.total_payment)
            tvPaymentWay.text = getString(R.string.choose_payment)
            btnPay.text = getString(R.string.buy)
        }
    }

    override fun initListener() {
        binding.checkoutToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeData() {}
}