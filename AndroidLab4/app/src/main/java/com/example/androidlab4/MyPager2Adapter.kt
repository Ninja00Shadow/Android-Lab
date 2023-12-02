package com.example.androidlab4

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPager2Adapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {
    val TAB_NUMBER = 2
    override fun getItemCount(): Int {
        return TAB_NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0)
            return StartOptionsFragment.newInstance()
        return ThemeOptionsFragment.newInstance()
    }
}