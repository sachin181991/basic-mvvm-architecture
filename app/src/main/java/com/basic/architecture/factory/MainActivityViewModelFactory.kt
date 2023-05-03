package com.basic.architecture.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basic.architecture.data.network.repositories.MainActivityRepository
import com.basic.architecture.viewModels.MainActivityViewModel

class MainActivityViewModelFactory(
    private val repository: MainActivityRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}