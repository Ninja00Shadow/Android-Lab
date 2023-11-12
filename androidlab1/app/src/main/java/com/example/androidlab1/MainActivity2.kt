package com.example.androidlab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch

class MainActivity2 : AppCompatActivity() {
    lateinit var img1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val swListener = CompoundButton.OnCheckedChangeListener {_, isChecked ->
            if (isChecked) {
                img1.visibility = ImageView.VISIBLE
            } else {
                img1.visibility = ImageView.INVISIBLE
            }
        }

        img1 = findViewById(R.id.img1)
        val sw: Switch = findViewById(R.id.switch1)
        sw.isChecked = true
        sw.setOnCheckedChangeListener(swListener)
    }
}