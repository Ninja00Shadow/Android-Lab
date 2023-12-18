package com.example.androidlab6

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab6.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyImage()

        parentFragmentManager.setFragmentResultListener("startScreen", this) { _, bundle ->
            val title = bundle.getString("title")
            val description = bundle.getString("description")

            binding.mainTitle.text = title
            binding.mainDescription.text = description
        }

        parentFragmentManager.setFragmentResultListener("image", this) { _, bundle ->
            val image = bundle.getInt("image")
            saveImage(image)
            applyImage()
        }

        parentFragmentManager.setFragmentResultListener("imageUri", this) { _, bundle ->
            val uri = bundle.getParcelable("uri") as? android.net.Uri
            saveImage(uri)
            applyImage()
        }

        binding.sharedStorageRadioButton.setOnClickListener {
            val dataRepo = ImageRepo.getinstance(requireContext())
            dataRepo.setStorageType(ImageRepo.SHARED_STORAGE)
        }

        binding.privateStorageRadioButton.setOnClickListener {
            val dataRepo = ImageRepo.getinstance(requireContext())
            dataRepo.setStorageType(ImageRepo.PRIVATE_STORAGE)
        }
    }

    private fun applyImage() {
//        val data : SharedPreferences = requireActivity().getSharedPreferences("image", Context.MODE_PRIVATE)
//        val image = data.getInt("image", R.drawable.wroclaw)
//
//        binding.mainImage.setImageResource(image)

        val data : SharedPreferences = requireActivity().getSharedPreferences("uri", Context.MODE_PRIVATE)
        val uri = data.getString("uri", null)

        if (uri != null) {
            binding.mainImage.setImageURI(android.net.Uri.parse(uri))
        }
        else {
            binding.mainImage.setImageResource(R.drawable.wroclaw)
        }
    }

    fun saveImage(image: Int) {
        val data : SharedPreferences = requireActivity().getSharedPreferences("image", Context.MODE_PRIVATE)

        val editor : SharedPreferences.Editor = data.edit()
        editor.putInt("image", image)
        editor.apply()
    }

    fun saveImage(uri: android.net.Uri?) {
        val data : SharedPreferences = requireActivity().getSharedPreferences("uri", Context.MODE_PRIVATE)

        val editor : SharedPreferences.Editor = data.edit()
        editor.putString("uri", uri.toString())
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}