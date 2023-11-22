package com.example.androidlab2

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.androidlab2.databinding.ActivityTrailBinding

class TrailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrailBinding
    private lateinit var chosenTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chosenTheme = intent.getStringExtra("theme").toString()

        applyTheme()

//        setTheme()

        binding = ActivityTrailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.returnButton.setOnClickListener {
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun applyTheme() {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)

        setTheme(
            when (prefs.getInt("theme", 0)) {
                0 -> when (chosenTheme) {
                    "red" -> R.style.RedTrailTheme
                    "green" -> R.style.GreenTrailTheme
                    "blue" -> R.style.BlueTrailTheme
                    else -> R.style.RedTrailTheme
                }
                1 -> when (chosenTheme) {
                    "red" -> R.style.RedTrailTheme_Dark
                    "green" -> R.style.GreenTrailTheme_Dark
                    "blue" -> R.style.BlueTrailTheme_Dark
                    else -> R.style.RedTrailTheme_Dark
                }
                else -> R.style.RedTrailTheme
            }
        )

    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.hm_trail_theme, menu)
//
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.red_trail_theme -> {
//                chosenTheme = "red"
//            }
//            R.id.green_trail_theme -> {
//                chosenTheme = "green"
//            }
//            R.id.blue_trail_theme -> {
//                chosenTheme = "blue"
//            }
//        }
//
//        intent.putExtra("theme", chosenTheme)
//        recreate()
//        return true
//    }

//    private fun setTheme() {
//        setTheme(
//            when (chosenTheme) {
//                "red" -> R.style.RedTrailTheme
//                "green" -> R.style.GreenTrailTheme
//                "blue" -> R.style.BlueTrailTheme
//                else -> R.style.RedTrailTheme
//            }
//        )
//    }
}