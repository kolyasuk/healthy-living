package com.lilkoln.healthyliving.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lilkoln.healthyliving.R
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