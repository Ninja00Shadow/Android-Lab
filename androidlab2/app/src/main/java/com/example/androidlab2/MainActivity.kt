package com.example.androidlab2

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.androidlab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private var fontSizeChecked = booleanArrayOf(true, false, false)
    private var fontStyleChecked = booleanArrayOf(false, false)
    private var fontColorChecked = booleanArrayOf(true, false, false)

    private var trailThemeStyle = "red"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        registerForContextMenu(binding.fontSizeTextView)
        registerForContextMenu(binding.fontStyleTextView)
        registerForContextMenu(binding.fontColorTextView)

        binding.trailButton.setOnClickListener {
            val intent = Intent(this, TrailActivity::class.java)
            intent.putExtra("theme", trailThemeStyle)
            startActivity(intent)
        }

        binding.lightThemeButton.setOnClickListener {
            setPrefs(0)
            recreate()
        }

        binding.darkThemeButton.setOnClickListener {
            setPrefs(1)
            recreate()
        }

    }

    private fun applyTheme() {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)

        when (prefs.getInt("theme", 0)) {
            0 -> setTheme(R.style.Theme_Androidlab2)
            1 -> setTheme(R.style.Theme_Androidlab2_Dark)
            else -> setTheme(R.style.Theme_Androidlab2)
        }
    }

    private fun setPrefs(i: Int) {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt("theme", i)
        editor.apply()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = menuInflater

        if (v == binding.fontSizeTextView) {
            menu?.setHeaderTitle("Font Size")
            inflater.inflate(R.menu.cm_font_size, menu)

            for (i in fontSizeChecked.indices) {
                if (fontSizeChecked[i])
                    menu?.getItem(i)?.isChecked = true
            }

            return super.onCreateContextMenu(menu, v, menuInfo)
        }

        if (v == binding.fontStyleTextView) {
            menu?.setHeaderTitle("Font Style")
            inflater.inflate(R.menu.cm_font_style, menu)

            for (i in fontStyleChecked.indices) {
                if (fontStyleChecked[i])
                    menu?.getItem(i)?.isChecked = true
            }

            return super.onCreateContextMenu(menu, v, menuInfo)
        }

        if (v == binding.fontColorTextView) {
            menu?.setHeaderTitle("Font Color")
            inflater.inflate(R.menu.cm_font_color, menu)

            for (i in fontColorChecked.indices) {
                if (fontColorChecked[i])
                    menu?.getItem(i)?.isChecked = true
            }
        }

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.font_size_14sp -> {
                fontSizeChecked = booleanArrayOf(true, false, false)

                binding.fullNameTextView.textSize = 14f
                binding.fontSizeTextView.text = "Font Size: 14sp"
                return true
            }
            R.id.font_size_20sp -> {
                fontSizeChecked = booleanArrayOf(false, true, false)

                binding.fullNameTextView.textSize = 20f
                binding.fontSizeTextView.text = "Font Size: 20sp"
                return true
            }
            R.id.font_size_30sp -> {
                fontSizeChecked = booleanArrayOf(false, false, true)

                binding.fullNameTextView.textSize = 30f
                binding.fontSizeTextView.text = "Font Size: 30sp"
                return true
            }
            R.id.font_style_bold -> {
                item.isChecked = !item.isChecked
                fontStyleChecked[0] = !fontStyleChecked[0]


                chooseTextStyle()
                return true
            }
            R.id.font_style_italic -> {
                item.isChecked = !item.isChecked
                fontStyleChecked[1] = !fontStyleChecked[1]

                chooseTextStyle()
                return true
            }
            R.id.font_color_black -> {
                fontColorChecked = booleanArrayOf(true, false, false)

                binding.fullNameTextView.setTextColor(Color.BLACK)
                binding.fontColorTextView.text = "Font Color: Black"
                return true
            }
            R.id.font_color_red -> {
                fontColorChecked = booleanArrayOf(false, true, false)

                binding.fullNameTextView.setTextColor(Color.RED)
                binding.fontColorTextView.text = "Font Color: Red"
                return true
            }
            R.id.font_color_blue -> {
                fontColorChecked = booleanArrayOf(false, false, true)

                binding.fullNameTextView.setTextColor(Color.BLUE)
                binding.fontColorTextView.text = "Font Color: Blue"
                return true
            }
        }

        return super.onContextItemSelected(item)
    }

    private fun chooseTextStyle() {
        if (fontStyleChecked[0] && fontStyleChecked[1]) {
            binding.fullNameTextView.setTypeface(null, Typeface.BOLD_ITALIC)
            binding.fontStyleTextView.text = "Font Style: Bold Italic"
        }
        if (fontStyleChecked[0] && !fontStyleChecked[1]) {
            binding.fullNameTextView.setTypeface(null, Typeface.BOLD)
            binding.fontStyleTextView.text = "Font Style: Bold"
        }
        else if (!fontStyleChecked[0] && fontStyleChecked[1]) {
            binding.fullNameTextView.setTypeface(null, Typeface.ITALIC)
            binding.fontStyleTextView.text = "Font Style: Italic"
        }
        else {
            binding.fullNameTextView.setTypeface(null, Typeface.NORMAL)
            binding.fontStyleTextView.text = "Font Style: Normal"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.hm_trail_theme, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.red_trail_theme -> {
                trailThemeStyle = "red"
            }
            R.id.green_trail_theme -> {
                trailThemeStyle = "green"
            }
            R.id.blue_trail_theme -> {
                trailThemeStyle = "blue"
            }
        }
        return super.onOptionsItemSelected(item)
    }
}