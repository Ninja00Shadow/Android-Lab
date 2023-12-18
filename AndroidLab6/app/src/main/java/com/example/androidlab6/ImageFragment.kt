package com.example.androidlab6

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab6.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    private var image: Int = -1
    private var uri: Uri? = null

    private lateinit var binding: FragmentImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getInt("image")
            uri = it.getParcelable("uri")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (image != -1) {
            binding.imageImage.setImageResource(image)
        }
        if (uri != null) {
            binding.imageImage.setImageURI(uri)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(image : Int) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt("image", image)
                }
            }

        fun newInstance(uri: Uri?) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("uri", uri)
                }
            }
    }
}