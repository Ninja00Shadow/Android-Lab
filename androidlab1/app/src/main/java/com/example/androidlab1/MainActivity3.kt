package com.example.androidlab1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidlab1.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity(), OnLongClickListener {
    lateinit var binding: ActivityMain3Binding
    var startCalcForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val result = data?.getIntExtra("result", 999)

            if (result != null) {
                val str = result.toString()
                val textViewResults = binding.tv4Results
                textViewResults.text = str
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val activity3 = binding.activity3
        activity3.setOnLongClickListener(this)

        val et1 = binding.et1
        val button_dial = binding.buttonDial
        button_dial.setOnClickListener {
            val str1 = et1.text
            runDial(str1.toString())
        }

        val button_calc = binding.buttonCalc
        var et_val1 = binding.etVal1
        var et_val2 = binding.etVal2
        var aBundle = Bundle()

        button_calc.setOnClickListener {
            aBundle.putInt("val1", et_val1.text.toString().toInt())
            aBundle.putInt("val2", et_val2.text.toString().toInt())
            val intent = Intent(this, ActivityCalc::class.java)
            intent.putExtras(aBundle)

            startCalcForResult.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(applicationContext, "Activity 3 is started", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onLongClick(view: View?): Boolean {
        onBackPressed()
        return true
    }

    fun finishActivity3(view: View) {
        onBackPressed()
    }

    private fun runDial(phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNum")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}