package com.lilkoln.healthyliving.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.UserRepository

class MainActivityViewModelFactory(private val repository: FoodRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}