package com.example.androidlab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab4.databinding.FragmentSwipeBinding
import com.google.android.material.tabs.TabLayoutMediator

class SwipeFragment : Fragment() {
    private lateinit var binding: FragmentSwipeBinding
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
        binding  = FragmentSwipeBinding.inflate(inflater, container, false)
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
    }

    companion object {
        @JvmStatic
        fun newInstance() = SwipeFragment()
    }
}