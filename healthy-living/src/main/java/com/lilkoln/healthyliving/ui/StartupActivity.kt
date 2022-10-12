package com.lilkoln.healthyliving.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.R
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.UserRepository
import com.lilkoln.healthyliving.ui.register.UsernameActivity
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModel
import com.lilkoln.healthyliving.ui.register.UsernameActivityViewModelFactory

class StartupActivity : AppCompatActivity() {
    private lateinit var usernameViewModel: UsernameActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)
        val db = AppDataBase.getUserDataBase(this)!!
        checkUserExistence(db)
    }

    private fun checkUserExistence(db: AppDataBase) {
        val userRepository = UserRepository(db.getUserDao())
        usernameViewModel = ViewModelProvider(this, UsernameActivityViewModelFactory(userRepository)).get(UsernameActivityViewModel::class.java)

        usernameViewModel.loadUser()
        usernameViewModel.user.observe(this) { user ->
            if (user == null)
                setUsernameCreateActivity()
            else setMainActivity()
        }
    }

    private fun setUsernameCreateActivity() {
        val usernameActivity = Intent(this, UsernameActivity::class.java)
        startActivity(usernameActivity)
    }

    private fun setMainActivity() {
        val main = Intent(this, MainActivity::class.java)
        startActivity(main)
    }
}