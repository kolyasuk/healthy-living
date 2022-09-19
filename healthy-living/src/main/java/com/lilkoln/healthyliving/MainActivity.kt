package com.lilkoln.healthyliving

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UsernameActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDataBase::class.java, "healthy_living_db").build()
        val userRepository = UserRepository(db.getUserDao())
        viewModel = ViewModelProvider(this, UsernameActivityViewModelFactory(userRepository)).get(UsernameActivityViewModel::class.java)

        randomButton.setOnClickListener { randomAction() }

    }

    private fun randomAction() {
        val usernameActivity = Intent(this, UsernameActivity::class.java)

        startActivity(usernameActivity)
    }


}