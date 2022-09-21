package com.lilkoln.healthyliving.ui.food

import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.Food
import com.lilkoln.healthyliving.db.repository.FoodRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: FoodRepository) : ViewModel() {

    val _food = MutableLiveData<Food>()
    val food: LiveData<Food>
        get() = _food

    fun createFood(food: Food) {
        viewModelScope.launch {
            repository.createFood(food)
            _food.value = food
        }
    }

    fun loadFood() {
        viewModelScope.launch {
            val food = repository.findAll()
            _food.value = food[0]
        }
    }

}