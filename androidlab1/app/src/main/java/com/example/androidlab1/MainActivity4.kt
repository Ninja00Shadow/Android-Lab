package com.example.androidlab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidlab1.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    var textToDisplay = ""
    var checkbox1Clicked = false
    var checkbox2Clicked = false
    var useToast = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkBox1.setOnClickListener { checkbox1Clicked = !checkbox1Clicked }
        binding.checkBox2.setOnClickListener { checkbox2Clicked = !checkbox2Clicked }

        binding.radioGroup1.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton1 -> useToast = true
                R.id.radioButton2 -> useToast = false
            }
        }

        binding.button.setOnClickListener() {
            if (checkbox1Clicked && checkbox2Clicked) {
                textToDisplay = "Funny Meme"
            }
            else if (checkbox1Clicked && !checkbox2Clicked) {
                textToDisplay = "Funny"
            }
            else if (!checkbox1Clicked && checkbox2Clicked) {
                textToDisplay = "Meme"
            }
            else {
                textToDisplay = "Nothing"
            }

            if (useToast) {
                binding.textView9.visibility = android.view.View.INVISIBLE

                val toast = Toast.makeText(applicationContext, textToDisplay, Toast.LENGTH_LONG)
                toast.show()
            } else {
                binding.textView9.text = textToDisplay
                binding.textView9.visibility = android.view.View.VISIBLE
            }
        }

    }
}