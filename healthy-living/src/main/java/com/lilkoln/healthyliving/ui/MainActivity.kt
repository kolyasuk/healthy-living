package com.lilkoln.healthyliving.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.FoodRepository
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.food.MainActivityViewModel
import com.lilkoln.healthyliving.ui.food.MainActivityViewModelFactory
import com.lilkoln.healthyliving.ui.register.UsernameActivity
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModel
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var usernameViewModel: UsernameActivityViewModel
    private lateinit var mainViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDataBase.getUserDataBase(this)!!
        checkUserExistence(db)

        val foodRepository = FoodRepository(db.getFoodDao())
        mainViewModel = ViewModelProvider(this, MainActivityViewModelFactory(foodRepository)).get(MainActivityViewModel::class.java)

        mainViewModel.loadFood()
        mainViewModel.food.observe(this) { food ->
            println(food)
        }

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