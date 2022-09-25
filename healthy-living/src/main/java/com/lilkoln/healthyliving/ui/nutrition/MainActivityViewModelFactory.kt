package com.lilkoln.healthyliving.ui.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository

class MainActivityViewModelFactory(private val foodRepository: FoodRepository,
                                   private val nutritionUnitRepository: NutritionUnitRepository,
                                   private val nutritionRepository: NutritionRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MainActivityViewModel(foodRepository, nutritionUnitRepository, nutritionRepository) as T
    }
}