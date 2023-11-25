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

class DogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dogSubmitButton = view.findViewById<View>(R.id.dogSubmitButton)

        dogSubmitButton?.setOnClickListener {
            Log.i("CatFragment","Cat button clicked")
            parentFragmentManager.setFragmentResult("messageFromChild", Bundle().apply {
                putString("message", createDogMessage())
            })
        }
    }

    private fun createDogMessage(): String {
        val name = view?.findViewById<android.widget.EditText>(R.id.dogNameEditText)?.text.toString()

        val breed = when (view?.findViewById<RadioGroup>(R.id.radioGroup)?.checkedRadioButtonId) {
            R.id.goldenRetrieverButton -> "Golden Retriever"
            R.id.jamnikButton -> "Sausage Dog"
            R.id.pitbulButton -> "Pitbul"
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

        return "Dog:\n $name $breed with $furColor fur"
    }

    companion object {
        @JvmStatic
        fun newInstance() = DogFragment()
    }
}