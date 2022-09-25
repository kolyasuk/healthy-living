package com.lilkoln.healthyliving.ui.food

import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.dao.NutritionUnitDao
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import kotlinx.coroutines.launch

class FoodMenuActivityViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    val _food = MutableLiveData<Food>()
    val food: LiveData<Food>
        get() = _food

    fun createFood(food: Food) {
        viewModelScope.launch {
            foodRepository.createFood(food)
            _food.value = food
        }
    }

    fun loadFood() {
        viewModelScope.launch {
            val food = foodRepository.findAll()
            _food.value = food[0]
        }
    }

}