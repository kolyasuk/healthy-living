package com.lilkoln.healthyliving

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.repository.UserRepository

class MainActivityViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}