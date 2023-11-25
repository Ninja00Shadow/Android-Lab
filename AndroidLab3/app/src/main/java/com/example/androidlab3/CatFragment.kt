package com.example.androidlab3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
                putString("message", "Cat")
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatFragment()
    }
}