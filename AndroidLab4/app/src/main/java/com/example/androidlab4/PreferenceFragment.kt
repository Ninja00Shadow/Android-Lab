package com.example.androidlab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.androidlab4.databinding.FragmentPreferenceBinding
import com.google.android.material.tabs.TabLayoutMediator

class PreferenceFragment : Fragment() {
    private lateinit var binding: FragmentPreferenceBinding
    private lateinit var adapter: MyPager2Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MyPager2Adapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentPreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = adapter

        val tabIcons = arrayOf(R.drawable.ic_options_startscreen, R.drawable.ic_options_theme)

        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Start Screen"
                    tab.setIcon(tabIcons[position])
                }
                1 -> {
                    tab.text = "Theme"
                    tab.setIcon(tabIcons[position])
                }
            }
        }

        tabLayoutMediator.attach()

        childFragmentManager.setFragmentResultListener("startScreen", this) { _, bundle ->
            val title = bundle.getString("title")
            val description = bundle.getString("description")

            parentFragmentManager.setFragmentResult("startScreen", bundleOf("title" to title, "description" to description))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PreferenceFragment()
    }
}