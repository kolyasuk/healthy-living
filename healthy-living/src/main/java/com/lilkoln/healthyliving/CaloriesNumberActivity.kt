package com.lilkoln.healthyliving

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.User
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
        viewModel = ViewModelProvider(this, UsernameActivityViewModelFactory(userRepository)).get(UsernameActivityViewModel::class.java)

        val user:User = intent.getSerializableExtra(USER) as User

        addUserCalories.setOnClickListener {
            viewModel.createUser(user)
        }
    }
}