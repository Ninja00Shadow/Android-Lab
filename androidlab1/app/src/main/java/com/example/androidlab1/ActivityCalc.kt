package com.example.androidlab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityCalc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val intent = intent
        val bundle = intent.extras

        val val1 = bundle?.getInt("val1", 1) ?: 0
        val val2 = bundle?.getInt("val2", 1) ?: 0

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val sum = val1 + val2
            val intent = intent
            intent.putExtra("result", sum)
            setResult(RESULT_OK, intent)
            finish()
        }

        val buttonSub = findViewById<Button>(R.id.buttonSub)

        buttonSub.setOnClickListener {
            val sub = val1 - val2
            val intent = intent
            intent.putExtra("result", sub)
            setResult(RESULT_OK, intent)
            finish()
        }

        val buttonMul = findViewById<Button>(R.id.buttonMul)

        buttonMul.setOnClickListener {
            val mul = val1 * val2
            val intent = intent
            intent.putExtra("result", mul)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}