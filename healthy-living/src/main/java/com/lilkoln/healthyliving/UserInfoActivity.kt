package com.lilkoln.healthyliving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.User
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_username.*

class UserInfoActivity : AppCompatActivity() {

    companion object{
        const val USERNAME = "username"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val username = intent.getStringExtra(USERNAME)

        addUserInfo.setOnClickListener {
            val userAge = userAge.text.toString().toInt()
            val userWeight = userWeight.text.toString().toFloat()
            val userHeight = userHeight.text.toString().toFloat()

            val user = User(null, username, userAge, userWeight, userHeight)

            val caloriesNumberActivity = Intent(this, CaloriesNumberActivity::class.java)

            caloriesNumberActivity.putExtra(CaloriesNumberActivity.USER, user)
            startActivity(caloriesNumberActivity)
        }

    }
}