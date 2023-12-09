package com.example.androidlab5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlab5.databinding.FragmentImageSwipeBinding

class ImageSwipeFragment : Fragment() {
    private lateinit var binding: FragmentImageSwipeBinding
    private lateinit var adapter: ImagePagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImagePagerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageSwipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.startingImages
        viewPager.adapter = adapter

        binding.setImageButton.setOnClickListener {
            val item = viewPager.currentItem
            var image = -1

            when (item) {
                0 -> image = R.drawable.wroclaw
                1 -> image = R.drawable.tokio
                2 -> image = R.drawable.new_york
            }

            parentFragmentManager.setFragmentResult("image", Bundle().apply {
                putInt("image", image)
            })

            findNavController().navigate(R.id.action_global_mainFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImageSwipeFragment()
    }
}