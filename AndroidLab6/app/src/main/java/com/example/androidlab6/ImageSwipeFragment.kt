package com.example.androidlab6

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlab6.databinding.FragmentImageSwipeBinding

class ImageSwipeFragment : Fragment() {
    private lateinit var binding: FragmentImageSwipeBinding
    private lateinit var adapter: ImagePagerAdapter

    private lateinit var imageRepo : ImageRepo

    private var startPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ImagePagerAdapter(this)

        arguments?.let {
            startPosition = it.getInt("startPosition")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageSwipeBinding.inflate(inflater, container, false)

        imageRepo = ImageRepo.getinstance(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.startingImages
        viewPager.adapter = adapter

        viewPager.post {
            viewPager.setCurrentItem(startPosition, false)
        }

        binding.setImageButton.setOnClickListener {
            val item = viewPager.currentItem
            var uri : Uri? = null

            val images = when(imageRepo.getStorageType()) {
                ImageRepo.SHARED_STORAGE -> imageRepo.getSharedList()
                else -> imageRepo.getAppList()
            }

            uri = images?.get(item)?.curi

            parentFragmentManager.setFragmentResult("imageUri", Bundle().apply {
                putParcelable("uri", uri)
            })
            findNavController().navigate(R.id.action_global_mainFragment)
        }

        binding.goBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_global_photoListFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImageSwipeFragment()

        fun newInstance(startPosition: Int) =
            ImageSwipeFragment().apply {
                arguments = Bundle().apply {
                    putInt("startPosition", startPosition)
                }
            }
    }
}