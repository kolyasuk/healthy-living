package com.lilkoln.healthyliving.ui.nutrition

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.lilkoln.healthyliving.db.entity.Food
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.enums.FoodType
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.NutritionModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.math.RoundingMode.valueOf
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

class MainActivityViewModel(private val userRepository: UserRepository,
                            private val foodRepository: FoodRepository,
                            private val nutritionUnitRepository: NutritionUnitRepository,
                            private val nutritionRepository: NutritionRepository
) : ViewModel() {

    private val _food = MutableLiveData<Food>()
    val food: LiveData<Food>
        get() = _food

    private val _nutrition = MutableLiveData<Nutrition>()
    val nutrition: LiveData<Nutrition>
        get() = _nutrition

    private val _nutritionModel = MutableLiveData<NutritionModel>()
    val nutritionModel: LiveData<NutritionModel>
        get() = _nutritionModel

    private val nutritionService = NutritionService(this)
    private val nutritionMap = HashMap<FoodType, Int>()

    @SuppressLint("NewApi")
    fun loadNutrition() {
        viewModelScope.launch {
            createNutrition()
            val obj = nutritionRepository.getNutritionUnitsByDate(LocalDate.now())
            _nutritionModel.value = NutritionModel(
                obj.lunch.id!!,
                createName(obj.lunch),
                obj.lunch.checked,
                obj.firstSnack.id!!,
                createName(obj.firstSnack),
                obj.firstSnack.checked,
                obj.dinner.id!!,
                createName(obj.dinner),
                obj.dinner.checked,
                obj.secondSnack.id!!,
                createName(obj.secondSnack),
                obj.secondSnack.checked,
                obj.supper.id!!,
                createName(obj.supper),
                obj.supper.checked
            )
        }
    }

    private suspend fun createName(nutritionUnit :NutritionUnit): String {
        val food = foodRepository.findFoodById(nutritionUnit.foodId)
        return food.name + " " + nutritionUnit.amount + " (" + nutritionUnit.calories + "k, " + nutritionUnit.proteins + "p )"
    }

    @SuppressLint("NewApi")
    private suspend fun createNutrition() {
        val exist = nutritionRepository.existForDate(LocalDate.now())
        if(!exist) {
            val lunch = foodRepository.getFoodByType(FoodType.LUNCH)
            val fstSnack1 = foodRepository.getFoodByType(FoodType.SNACK)
            val dinner = foodRepository.getFoodByType(FoodType.DINNER)
            val sndSnack = foodRepository.getFoodByType(FoodType.SNACK)
            val supper = foodRepository.getFoodByType(FoodType.SUPPER)

            val userCalories = userRepository.getUserCalories()
            nutritionService.constructNutritionUnit(userCalories, lunch[0], 22)
            nutritionService.constructNutritionUnit(userCalories, fstSnack1[0], 10)
            nutritionService.constructNutritionUnit(userCalories, dinner[0], 35)
            nutritionService.constructNutritionUnit(userCalories, sndSnack[0], 18)
            nutritionService.constructNutritionUnit(userCalories, supper[0], 15)

            nutritionService.constructNutrition(nutritionMap)
            nutritionMap.clear()
        }
    }

    fun checkNutritionUnit(id: Int, checked: Boolean) {
        viewModelScope.launch {
            nutritionUnitRepository.checkNutritionUnit(id, checked)
        }
    }

    suspend fun createNutritionUnit(nutritionUnit: NutritionUnit, type: FoodType) {
        val id = nutritionUnitRepository.createNutritionUnit(nutritionUnit)
        if(nutritionMap.containsKey(FoodType.SNACK) && type === FoodType.SNACK)
            nutritionMap[FoodType.SNACK_2] = id.toInt()
        else nutritionMap[type] = id.toInt()
    }

    @SuppressLint("NewApi")
    suspend fun createNutrition(nutrition: Nutrition) {
        val exist = nutritionRepository.existForDate(LocalDate.now())
        if(!exist){
            nutritionRepository.createNutrition(nutrition)
        }
    }

}