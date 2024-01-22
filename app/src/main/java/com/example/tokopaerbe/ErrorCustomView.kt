package com.example.tokopaerbe

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.tokopaerbe.databinding.ErrorCustomViewBinding

class ErrorCustomView @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: ErrorCustomViewBinding
    init{
        binding = ErrorCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setErrorMessage(title: String, description: String, btnTitle: String = "", action: () -> (Unit)) = with(binding) {
        tvError.text = title
        errorDesc.text = description
        btnRefresh.isVisible = btnTitle.isNotEmpty()
        btnRefresh.text = btnTitle
        btnRefresh.setOnClickListener{
            action.invoke()
        }
    }
}