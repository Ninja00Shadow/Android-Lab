package com.example.androidlab5

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagePagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val TAB_NUMBER = 3
    private var position = 0

    override fun createFragment(position: Int): Fragment {
        this.position = position
        if (position == 0)
            return ImageFragment.newInstance(R.drawable.wroclaw)
        if (position == 1)
            return ImageFragment.newInstance(R.drawable.tokio)
        return ImageFragment.newInstance(R.drawable.new_york)
    }

    override fun getItemCount(): Int {
        return TAB_NUMBER
    }

    fun getCurrentItem(): Int {
        return this.position
    }
}