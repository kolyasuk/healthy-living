package com.lilkoln.healthyliving

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.lilkoln.healthyliving.RandomActivity.Companion.TOTAL_COUNT
import com.lilkoln.healthyliving.db.User
import com.lilkoln.healthyliving.db.AppDataBase
import com.lilkoln.healthyliving.db.repository.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDataBase::class.java, "healthy_living_db").build()
        val userRepository = UserRepository(db.getUserDao())
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(userRepository)).get(MainActivityViewModel::class.java)

        toastButton.setOnClickListener { toastAction() }
        countButton.setOnClickListener { countAction() }
        randomButton.setOnClickListener { randomAction() }
        addName.setOnClickListener{
            val name = enterName.text.toString()

            viewModel.createUser(name)

            val toast = Toast.makeText(this, "added $name", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    private fun toastAction() {
        viewModel.loadFirstUser()
        viewModel.user.observe(this) { result ->
            val toast = Toast.makeText(this,  result.firstName, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun countAction() {
        val countString =  Integer.parseInt(countValue.text.toString()) + 1
        countValue.text = countString.toString()
    }

    private fun randomAction() {
        val randomIntent = Intent(this, RandomActivity::class.java)

        val count = Integer.parseInt(countValue.text.toString())
        randomIntent.putExtra(TOTAL_COUNT, count)

        startActivity(randomIntent);
    }


}