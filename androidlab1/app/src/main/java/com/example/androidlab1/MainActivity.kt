package com.example.androidlab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }

    fun openActivity4(view: View) {
        val intent = Intent(this, MainActivity4::class.java)
        startActivity(intent)
    }
}