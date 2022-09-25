package com.lilkoln.healthyliving.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.ui.MainActivity
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.entity.User
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.android.synthetic.main.activity_calories_number.*

class CaloriesNumberActivity : AppCompatActivity() {

    companion object{
        val USER = "user"
    }

    private lateinit var viewModel: UsernameActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_number)

        val db = AppDataBase.getUserDataBase(this)!!
        val userRepository = UserRepository(db.getUserDao())
        viewModel = ViewModelProvider(this, UsernameActivityViewModelFactory(userRepository)).get(
            UsernameActivityViewModel::class.java)


        addUserCalories.setOnClickListener {
            val userCalories = userCalories.text.toString().toInt()
            val user: User = intent.getSerializableExtra(USER) as User
            user.calories = userCalories
            viewModel.createUser(user)

            viewModel.user.observe(this) { createUser ->
                if(createUser != null)
                    setMainActivityy()
            }
        }
    }

    private fun setMainActivityy() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }
}