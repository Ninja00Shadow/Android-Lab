package com.example.androidlab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Switch

class CatFragment : Fragment() {

    private val items = listOf("Black", "Brown", "Yellow")
    private lateinit var list : AutoCompleteTextView;
    private lateinit var adapter : ArrayAdapter<String>;

    private var chosenFurColor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = view.findViewById(R.id.furColorAutoCompleteTextView)
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        list.setAdapter(adapter)

        list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Log.i("CatFragment", "Selected: $selectedItem")
            chosenFurColor = selectedItem
        }

        val catSubmitButton = view.findViewById<View>(R.id.catSubmitButton)

        catSubmitButton?.setOnClickListener {
            parentFragmentManager.setFragmentResult("messageFromChild", Bundle().apply {
                putString("message", createCatMessage())
            })
        }
    }

    private fun createCatMessage(): String {
        val ribbed = when (view?.findViewById<Switch>(R.id.ribbedSwitch)?.isChecked) {
            true -> "Ribbed"
            else -> ""
        }
        val breed = when (view?.findViewById<RadioGroup>(R.id.radioGroup)?.checkedRadioButtonId) {
            R.id.britishShorthairButton -> "British Shorthair"
            R.id.egyptianButton -> "Egyptian Mau"
            R.id.mixedButton -> "Mixed"
            else -> "Unknown"
        }
        val furColor = chosenFurColor

        return "Cat:\n $ribbed $breed with $furColor fur"
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatFragment()
    }
}