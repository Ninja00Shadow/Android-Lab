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
    private var icon = R.drawable.ic_launcher_foreground

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

        binding.itemIconRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.itemIconType1 -> icon = R.drawable.ic_new_city_1
                R.id.itemIconType2 -> icon = R.drawable.ic_new_city_2
                R.id.itemIconType3 -> icon = R.drawable.ic_new_city_3
            }
        }

        binding.saveButton.setOnClickListener {
            val name = binding.itemNameEditText.text.toString()
            val description = binding.itemDescriptionEditText.text.toString()
            val province = binding.itemProvinceEditText.text.toString()
            val population = binding.itemPopulationEditText.text.toString().toInt()
            val area = binding.itemAreaEditText.text.toString().toDouble()
            val icon = this.icon

            val item = DataItem(name, description, province, population, area, icon)
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