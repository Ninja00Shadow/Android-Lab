package com.example.androidlab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.FragmentTransaction

private const val TAG_FD = "FragmentDog"
private const val TAG_FC = "FragmentCat"

class MainFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    private lateinit var dogFragment: DogFragment
    private lateinit var catFragment: CatFragment
    private lateinit var myTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            dogFragment = DogFragment.newInstance()
            catFragment = CatFragment.newInstance()

            myTransaction = childFragmentManager.beginTransaction()
            myTransaction.add(R.id.dynamicFragmentContainer, dogFragment, TAG_FD)
            myTransaction.detach(dogFragment)
            myTransaction.add(R.id.dynamicFragmentContainer, catFragment, TAG_FC)
            myTransaction.detach(catFragment)
            myTransaction.commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = requireActivity().findViewById<RadioGroup>(R.id.animalChoiceRadioGroup)

        if (savedInstanceState != null) {
            dogFragment = childFragmentManager.findFragmentByTag(TAG_FD) as DogFragment
            catFragment = childFragmentManager.findFragmentByTag(TAG_FC) as CatFragment
        }

        radioGroup.setOnCheckedChangeListener(this)

        childFragmentManager.setFragmentResultListener("messageFromChild", this) { _, bundle ->
            val result = bundle.getString("message")
            val messageFromChildTextView = requireActivity().findViewById<android.widget.TextView>(R.id.resultTextView)
            messageFromChildTextView.text = result
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        myTransaction = childFragmentManager.beginTransaction()
        when (checkedId) {
            R.id.dogButton -> {
                myTransaction.detach(catFragment)
                myTransaction.attach(dogFragment)
            }
            R.id.catButton -> {
                myTransaction.detach(dogFragment)
                myTransaction.attach(catFragment)
            }
        }
        myTransaction.addToBackStack(null)
        myTransaction.commit()
    }
}