package com.example.androidlab4

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlab4.databinding.FragmentAddItemBinding

class AddItemFragment : Fragment() {
    private lateinit var binding: FragmentAddItemBinding
    private var size = CitySize.AVERAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            val item = DataItem(name, description, province, populationInt, rating, size)
            DataRepository.getInstance().addItem(item)

            findNavController().navigateUp()
        }

        binding.cancelButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cancel")
            builder.setMessage("Are you sure you want to cancel?")
            builder.setPositiveButton("Yes") { _, _ ->
                findNavController().navigateUp()
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