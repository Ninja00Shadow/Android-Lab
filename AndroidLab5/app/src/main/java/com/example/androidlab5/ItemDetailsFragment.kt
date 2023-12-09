package com.example.androidlab5

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.androidlab5.databinding.FragmentItemDetailsBinding

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

        Log.d("ItemDetailsFragment", "onViewCreated: $dataBundle")

        binding.modifyButton.setOnClickListener {
            val bundle = bundleOf(
                "id" to dataBundle?.getInt("id"),
                "name" to dataBundle?.getString("name"),
                "description" to dataBundle?.getString("description"),
                "province" to dataBundle?.getString("province"),
                "population" to dataBundle?.getInt("population"),
                "rating" to dataBundle?.getDouble("rating"),
                "size" to dataBundle?.getSerializable("size"),
            )
            Log.d("ItemDetailsFragment", "onModify: $bundle")
            findNavController().navigate(R.id.action_itemDetailsFragment_to_addItemFragment, bundle)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemDetailsFragment()
    }
}