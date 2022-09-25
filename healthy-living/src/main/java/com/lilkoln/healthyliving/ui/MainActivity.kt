package com.lilkoln.healthyliving.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.MeasureUnit
import com.lilkoln.healthyliving.db.entity.Nutrition
import com.lilkoln.healthyliving.db.entity.NutritionUnit
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModel
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModelFactory
import com.lilkoln.healthyliving.ui.register.UsernameActivity
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModel
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate.now
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var usernameViewModel: UsernameActivityViewModel
    private lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDataBase.getUserDataBase(this)!!
        checkUserExistence(db)

        val foodRepository = FoodRepository(db.getFoodDao())
        val nutritionUnitRepository = NutritionUnitRepository(db.getNutritionUnitDao())
        val nutritionRepository = NutritionRepository(db.getNutritionDao())
        mainViewModel = ViewModelProvider(this, MainActivityViewModelFactory(foodRepository, nutritionUnitRepository, nutritionRepository)).get(MainActivityViewModel::class.java)

        mainViewModel.loadFood()
        mainViewModel.food.observe(this) { food ->
            println(food)

//            var nu = NutritionUnit(null, food.id, 1F, MeasureUnit.NUMBER, false, food.calories, food.proteins)
//            mainViewModel.createNU(nu)

            var nutrition = Nutrition(null, 1, 1, 1, 1, 1, Date())
            mainViewModel.createNutrition(nutrition)

        }


        var nutritionModel = NutritionModel("Жарені яйця Жарені яйця Жарені яйця", "Яблука",
            "Гречка з куркою", "Протеїн", "Салат капрезе")
        setDayMenu(nutritionModel)


        lunchView.setOnClickListener {
            setStrikeThruValue(lunchView, !lunchView.paint.isStrikeThruText)
        }

        firstSnackView.setOnClickListener {
            setStrikeThruValue(firstSnackView, !firstSnackView.paint.isStrikeThruText)
        }

        dinnerView.setOnClickListener {
            setStrikeThruValue(dinnerView, !dinnerView.paint.isStrikeThruText)
        }

        secondSnackView.setOnClickListener {
            setStrikeThruValue(secondSnackView, !secondSnackView.paint.isStrikeThruText)
        }

        supperView.setOnClickListener {
            setStrikeThruValue(supperView, !supperView.paint.isStrikeThruText)
        }

    }

    private fun setDayMenu(nutritionModel: NutritionModel) {
        lunchView.text = nutritionModel.lunchMeal
        firstSnackView.text = nutritionModel.firstSnack
        dinnerView.text = nutritionModel.dinnerMeal
        secondSnackView.text = nutritionModel.secondSnack
        supperView.text = nutritionModel.supperMeal
    }

    private fun setStrikeThruValue(view: TextView, setStrikeThru: Boolean) {
        var strikeFlag = 0
        if(setStrikeThru)
            strikeFlag = Paint.STRIKE_THRU_TEXT_FLAG

        view.paintFlags = strikeFlag
    }

    private fun checkUserExistence(db: AppDataBase) {
        val userRepository = UserRepository(db.getUserDao())
        usernameViewModel = ViewModelProvider(this, UsernameActivityViewModelFactory(userRepository)).get(UsernameActivityViewModel::class.java)

        usernameViewModel.loadUser()
        usernameViewModel.user.observe(this) { user ->
            if (user == null)
                setUsernameCreateActivity()
        }
    }

    private fun setUsernameCreateActivity() {
        val usernameActivity = Intent(this, UsernameActivity::class.java)
        startActivity(usernameActivity)
    }

}