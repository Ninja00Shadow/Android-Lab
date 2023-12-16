package com.example.androidlab6

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlab6.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private var size = CitySize.AVERAGE
    private var dataBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBundle = arguments
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dataBundle != null) {
            binding.itemNameEditText.setText(dataBundle?.getString("name"))
            binding.itemDescriptionEditText.setText(dataBundle?.getString("description"))
            binding.itemProvinceEditText.setText(dataBundle?.getString("province"))
            binding.itemPopulationEditText.setText(dataBundle?.getInt("population").toString())
            binding.itemRatingEdit.rating = dataBundle?.getDouble("rating")!!.toFloat()
            size = dataBundle?.getSerializable("size") as CitySize

            when (size) {
                CitySize.SMALL -> {
                    binding.itemSizeSmall.isChecked = true
                    binding.itemSizeAverage.isChecked = false
                    binding.itemSizeBig.isChecked = false
                }
                CitySize.AVERAGE -> {
                    binding.itemSizeSmall.isChecked = false
                    binding.itemSizeAverage.isChecked = true
                    binding.itemSizeBig.isChecked = false
                }
                CitySize.BIG -> {
                    binding.itemSizeSmall.isChecked = false
                    binding.itemSizeAverage.isChecked = false
                    binding.itemSizeBig.isChecked = true
                }
            }
        }

        binding.itemSizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.itemSizeSmall -> size = CitySize.SMALL
                R.id.itemSizeAverage -> size = CitySize.AVERAGE
                R.id.itemSizeBig -> size = CitySize.BIG
            }
        }

        binding.saveButton.setOnClickListener {
            val name = binding.itemNameEditText.text.toString()
            val description = binding.itemDescriptionEditText.text.toString()
            val province = binding.itemProvinceEditText.text.toString()
            var population = binding.itemPopulationEditText.text.toString()
            if (population == "") {
                population = "0"
            }
            val populationInt = population.toInt()
            val rating = binding.itemRatingEdit.rating.toDouble()

            if (dataBundle != null) {
                val item = DBItem(name, description, province, populationInt, rating, size)
                item.id = dataBundle?.getInt("id")!!

                if (MyRepository.getInstance(requireContext()).modifyItem(item))
                    parentFragmentManager.setFragmentResult("itemUpdated", Bundle.EMPTY)
            }
            else {
                val item = DBItem(name, description, province, populationInt, rating, size)

                if (MyRepository.getInstance(requireContext()).addItem(item))
                    parentFragmentManager.setFragmentResult("itemAdded", Bundle.EMPTY)
            }
            findNavController().navigate(R.id.action_global_listFragment)
        }

        binding.cancelButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cancel")
            builder.setMessage("Are you sure you want to cancel?")
            builder.setPositiveButton("Yes") { _, _ ->
//                findNavController().navigateUp()
                requireActivity().onBackPressed()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddItemFragment()
    }
}