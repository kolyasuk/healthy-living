package com.lilkoln.healthyliving.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.repository.UserRepository

class UsernameActivityViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return UsernameActivityViewModel(repository) as T
    }
}