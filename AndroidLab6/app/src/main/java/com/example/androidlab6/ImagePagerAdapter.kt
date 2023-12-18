package com.example.androidlab6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagePagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var position = 0
    private val imageRepo = ImageRepo.getinstance(fragment.requireContext())

    override fun createFragment(position: Int): Fragment {
        this.position = position

        val images = when(imageRepo.getStorageType()) {
            ImageRepo.SHARED_STORAGE -> imageRepo.getSharedList()
            ImageRepo.PRIVATE_STORAGE -> imageRepo.getAppList()
            else -> return ImageFragment.newInstance(R.drawable.wroclaw)
        }

        if (!images.isNullOrEmpty()) {
            return ImageFragment.newInstance(images[position].curi)
        }

        return ImageFragment.newInstance(R.drawable.wroclaw)
    }

    override fun getItemCount(): Int {
        val images = when(imageRepo.getStorageType()) {
            ImageRepo.SHARED_STORAGE -> imageRepo.getSharedList()
            ImageRepo.PRIVATE_STORAGE -> imageRepo.getAppList()
            else -> null
        }

        return images?.size ?: 0
    }

    fun getCurrentItem(): Int {
        return this.position
    }
}