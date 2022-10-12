package com.lilkoln.healthyliving.ui

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModel
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async


class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = AppDataBase.getUserDataBase(this)!!

        val userRepository = UserRepository(db.getUserDao())
        val foodRepository = FoodRepository(db.getFoodDao())
        val nutritionUnitRepository = NutritionUnitRepository(db.getNutritionUnitDao())
        val nutritionRepository = NutritionRepository(db.getNutritionDao())
        mainViewModel = ViewModelProvider(this, MainActivityViewModelFactory(userRepository, foodRepository, nutritionUnitRepository, nutritionRepository)).get(MainActivityViewModel::class.java)

        mainViewModel.loadNutrition()
        mainViewModel.nutritionModel.observe(this) { nutritionModel ->
            setDayMenu(nutritionModel)
        }

        lunchView.setOnClickListener {
            checkNutrition(lunchView, !lunchView.paint.isStrikeThruText)
        }

        firstSnackView.setOnClickListener {
            checkNutrition(firstSnackView, !firstSnackView.paint.isStrikeThruText)
        }

        dinnerView.setOnClickListener {
            checkNutrition(dinnerView, !dinnerView.paint.isStrikeThruText)
        }

        secondSnackView.setOnClickListener {
            checkNutrition(secondSnackView, !secondSnackView.paint.isStrikeThruText)
        }

        supperView.setOnClickListener {
            checkNutrition(supperView, !supperView.paint.isStrikeThruText)
        }

    }

    private fun setDayMenu(nutritionModel: NutritionModel) {
        lunchView.setTag(R.id.nutritionUnitId, nutritionModel.lunchId)
        lunchView.text = nutritionModel.lunchMeal
        setStrikeThruValue(lunchView, nutritionModel.lunchChecked)
        firstSnackView.setTag(R.id.nutritionUnitId, nutritionModel.firstSnackId)
        firstSnackView.text = nutritionModel.firstSnack
        setStrikeThruValue(firstSnackView, nutritionModel.firstSnackChecked)
        dinnerView.setTag(R.id.nutritionUnitId, nutritionModel.dinnerId)
        dinnerView.text = nutritionModel.dinnerMeal
        setStrikeThruValue(dinnerView, nutritionModel.dinnerChecked)
        secondSnackView.setTag(R.id.nutritionUnitId, nutritionModel.secondSnackId)
        secondSnackView.text = nutritionModel.secondSnack
        setStrikeThruValue(secondSnackView, nutritionModel.secondSnackChecked)
        supperView.setTag(R.id.nutritionUnitId, nutritionModel.supperId)
        supperView.text = nutritionModel.supperMeal
        setStrikeThruValue(supperView, nutritionModel.supperChecked)
    }

    private fun checkNutrition(view: TextView, setStrikeThru: Boolean) {
        val id = view.getTag(R.id.nutritionUnitId).toString().toInt()
        mainViewModel.checkNutritionUnit(id, setStrikeThru)
        setStrikeThruValue(view, setStrikeThru)
    }

    private fun setStrikeThruValue(view: TextView, setStrikeThru: Boolean) {
        var strikeFlag = 0
        if(setStrikeThru)
            strikeFlag = Paint.STRIKE_THRU_TEXT_FLAG

        view.paintFlags = strikeFlag
    }

}