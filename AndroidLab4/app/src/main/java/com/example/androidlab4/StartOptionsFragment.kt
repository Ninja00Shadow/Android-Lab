package com.example.androidlab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlab4.databinding.FragmentStartOptionsBinding

class StartOptionsFragment : Fragment() {
    private lateinit var binding: FragmentStartOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentStartOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startScreenSetButton.setOnClickListener {
            val newTitle = binding.titleEditText.text.toString()
            val newDescription = binding.descriptionEditText.text.toString()

            val bundle = Bundle()
            bundle.putString("title", newTitle)
            bundle.putString("description", newDescription)

            parentFragmentManager.setFragmentResult("startScreen", bundle)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartOptionsFragment()
    }
}