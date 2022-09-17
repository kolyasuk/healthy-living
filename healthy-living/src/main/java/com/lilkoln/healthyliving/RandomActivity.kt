package com.lilkoln.healthyliving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class RandomActivity : AppCompatActivity() {

    lateinit var textViewRandomValue: TextView
    lateinit var textViewTitleText: TextView

    companion object{
        const val TOTAL_COUNT = "total count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        textViewRandomValue = findViewById(R.id.randomValue)
        textViewTitleText = findViewById(R.id.titleText)

        showRandomNumber()
    }

    fun showRandomNumber(){
        val count = intent.getIntExtra(TOTAL_COUNT, 0);

        if(count > 0){
            val randomInt = Random().nextInt(count + 1)

            textViewTitleText.text = getString(R.string.random_title, count)
            textViewRandomValue.text = randomInt.toString()
        }
    }
}