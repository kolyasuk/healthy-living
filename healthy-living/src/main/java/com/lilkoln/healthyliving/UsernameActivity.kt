package com.lilkoln.healthyliving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.AppDataBase.Companion.getUserDataBase
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.android.synthetic.main.activity_username.*

class UsernameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)

        addName.setOnClickListener{
            val name = username.text.toString()

            val userInfoActivity = Intent(this, UserInfoActivity::class.java)

            userInfoActivity.putExtra(UserInfoActivity.USERNAME, name)
            startActivity(userInfoActivity)
        }

    }
}