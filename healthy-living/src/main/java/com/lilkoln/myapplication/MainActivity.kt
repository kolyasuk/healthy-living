package com.lilkoln.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lilkoln.myapplication.RandomActivity.Companion.TOTAL_COUNT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toastButton.setOnClickListener { toastAction() }

        countButton.setOnClickListener { countAction() }

        randomButton.setOnClickListener { randomAction() }

    }

    private fun toastAction() {
        val toast = Toast.makeText(this, "Hello!!!!", Toast.LENGTH_SHORT)
        toast.show()
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