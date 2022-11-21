package com.lilkoln.healthyliving.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationView
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.NutritionRepository
import com.lilkoln.healthyliving.db.repository.NutritionUnitRepository
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModel
import com.lilkoln.healthyliving.ui.nutrition.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDataBase
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDataBase.getUserDataBase(this)!!

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val nawView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nawView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_parameters -> Toast.makeText(this, "My parameters", Toast.LENGTH_SHORT).show()
                R.id.nav_add_meal -> Toast.makeText(this, "Add meal", Toast.LENGTH_SHORT).show()
            }

            true
        }

        loadNutrition(db)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            loadUserInfo(db)
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun loadUserInfo(db: AppDataBase) {
        val userRepository = UserRepository(db.getUserDao())

        lifecycleScope.launch{
            val user = userRepository.getUser()
            headerUserName.text = user.firstName
            headerUserAge.text = user.age.toString() + "years"
        }

    }

    private fun loadNutrition(db: AppDataBase) {
        val userRepository = UserRepository(db.getUserDao())
        val foodRepository = FoodRepository(db.getFoodDao())
        val nutritionUnitRepository = NutritionUnitRepository(db.getNutritionUnitDao())
        val nutritionRepository = NutritionRepository(db.getNutritionDao())
        mainViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(
                userRepository,
                foodRepository,
                nutritionUnitRepository,
                nutritionRepository
            )
        ).get(MainActivityViewModel::class.java)

        mainViewModel.loadNutrition()
        mainViewModel.nutritionModel.observe(this) { nutritionModel ->
            setDayMenu(nutritionModel)
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