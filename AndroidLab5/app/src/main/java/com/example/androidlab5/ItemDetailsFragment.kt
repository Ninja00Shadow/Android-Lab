package com.example.androidlab4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.androidlab4.databinding.FragmentItemDetailsBinding

class ItemDetailsFragment : Fragment() {
    lateinit var binding: FragmentItemDetailsBinding
    private var dataBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            dataBundle = it
        }

        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemName.text = dataBundle?.getString("name")
        binding.itemDescription.text = dataBundle?.getString("description")
        binding.itemProvince.text = "Province: ${dataBundle?.getString("province")}"
        binding.itemPopulation.text = "Population: ${dataBundle?.getInt("population")}"
        binding.itemRating.rating = dataBundle?.getDouble("rating")!!.toFloat()
        binding.itemIcon.setImageResource(dataBundle?.getInt("icon")!!)

        binding.returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_global_listFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemDetailsFragment()
    }
}