package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.core.remote.service.Repository

class ViewModelFactory(private val repository: com.example.core.remote.service.Repository) : ViewModelProvider.NewInstanceFactory() {
}