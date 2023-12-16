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
    }

    private fun applyImage() {
        val data : SharedPreferences = requireActivity().getSharedPreferences("image", Context.MODE_PRIVATE)
        val image = data.getInt("image", R.drawable.wroclaw)

        binding.mainImage.setImageResource(image)
    }

    fun saveImage(image: Int) {
        val data : SharedPreferences = requireActivity().getSharedPreferences("image", Context.MODE_PRIVATE)

        val editor : SharedPreferences.Editor = data.edit()
        editor.putInt("image", image)
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}