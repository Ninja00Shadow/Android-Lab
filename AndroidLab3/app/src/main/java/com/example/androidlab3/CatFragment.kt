package com.example.androidlab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Switch

class CatFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catSubmitButton = view.findViewById<View>(R.id.catSubmitButton)

        catSubmitButton?.setOnClickListener {
            Log.i("CatFragment","Cat button clicked")
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
        val furColor = when (view?.findViewById<CheckBox>(R.id.blackFurCheckBox)?.isChecked) {
            true -> "Black"
            else -> ""
        } + " " + when (view?.findViewById<CheckBox>(R.id.brownFurCheckBox)?.isChecked) {
            true -> "Brown"
            else -> ""
        } + " " + when (view?.findViewById<CheckBox>(R.id.yellowFurCheckBox)?.isChecked) {
            true -> "Yellow"
            else -> ""
        }

        return "Cat:\n $ribbed $breed with $furColor fur"
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatFragment()
    }
}