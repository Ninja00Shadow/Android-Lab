package com.example.androidlab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view?.findViewById<View>(R.id.dogSubmitButton)?.setOnClickListener {
            parentFragmentManager.setFragmentResult("messageFromChild", Bundle().apply {
                putString("message", "Dog")
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DogFragment()
    }
}