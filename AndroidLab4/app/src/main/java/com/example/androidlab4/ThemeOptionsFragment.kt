package com.example.androidlab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab4.databinding.FragmentThemeOptionsBinding

class ThemeOptionsFragment : Fragment() {
    private lateinit var binding: FragmentThemeOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentThemeOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.theme1Button.setOnClickListener {
            setPrefs(1)
            requireActivity().recreate()
        }

        binding.theme2Button.setOnClickListener {
            setPrefs(2)
            requireActivity().recreate()
        }
    }

    private fun setPrefs(theme: Int) {
        val data = requireActivity().getSharedPreferences("my_prefs", 0)

        val editor = data.edit()
        editor.putInt("theme", theme)
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ThemeOptionsFragment()
    }
}