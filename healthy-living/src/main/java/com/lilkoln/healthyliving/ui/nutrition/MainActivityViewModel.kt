package com.lilkoln.healthyliving.ui.nutrition

import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.dao.NutritionUnitDao
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val foodRepository: FoodRepository,
                            private val nutritionUnitRepository: NutritionUnitRepository,
                            private val nutritionRepository: NutritionRepository
) : ViewModel() {

    val _food = MutableLiveData<Food>()
    val food: LiveData<Food>
        get() = _food

    val _nu = MutableLiveData<NutritionUnit>()
    val nu: LiveData<NutritionUnit>
        get() = _nu

    val _nutrition = MutableLiveData<Nutrition>()
    val nutrition: LiveData<Nutrition>
        get() = _nutrition

    fun loadFood() {
        viewModelScope.launch {
            val food = foodRepository.findAll()
            _food.value = food[0]
        }
    }

    fun createNU(nu: NutritionUnit) {
        viewModelScope.launch {
            nutritionUnitRepository.createNutritionUnit(nu)
            _nu.value = nu
            var list = nutritionUnitRepository.getNutritionUnitFood()
            println(list)
        }
    }

    fun createNutrition(nutrition: Nutrition) {
        viewModelScope.launch {
            nutritionRepository.createNutrition(nutrition)
            _nutrition.value = nutrition

            var list = nutritionRepository.getNutritionUnits(1)
            println(list)
        }
    }

}