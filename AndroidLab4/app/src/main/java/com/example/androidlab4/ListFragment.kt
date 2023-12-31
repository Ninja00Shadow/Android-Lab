package com.example.androidlab4

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab4.databinding.FragmentListBinding
import com.example.androidlab4.databinding.ListRowBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    var dataRepository = DataRepository.getInstance().getData()

    inner class MyAdapter(var data: MutableList<DataItem>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        inner class MyViewHolder(viewBinding : ListRowBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            val nameTextView: TextView = viewBinding.itemName
            val descriptionTextView: TextView = viewBinding.itemDescription
            val icon: ImageView = viewBinding.itemIcon
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val viewBinding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.nameTextView.text = data[position].name
            holder.descriptionTextView.text = data[position].description

            holder.itemView.setOnClickListener {
                val bundle = bundleOf(
                    "name" to data[position].name,
                    "description" to data[position].description,
                    "province" to data[position].province,
                    "population" to data[position].population,
                    "rating" to data[position].rating,
                    "size" to data[position].size,
                )
                findNavController().navigate(R.id.action_global_itemDetailsFragment, bundle)
            }

            when (data[position].size) {
                CitySize.AVERAGE -> holder.icon.setImageResource(R.drawable.ic_average_city)
                CitySize.SMALL -> holder.icon.setImageResource(R.drawable.ic_small_city)
                CitySize.BIG -> holder.icon.setImageResource(R.drawable.ic_big_city)
            }


//            val dataRepository = DataRepository.getinstance()

            holder.itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Delete item")
                builder.setMessage("Are you sure you want to delete this item?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    if (DataRepository.getInstance().deleteItem(position)) {
                        notifyDataSetChanged()
                    }
                }
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
                true
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataRepository = DataRepository.getInstance()

        Log.d("ListFragment", "onViewCreated: ${dataRepository.getData()}")

        val adapter = MyAdapter(dataRepository.getData())
        binding.recyklerView.adapter = adapter

        binding.recyklerView.layoutManager = LinearLayoutManager(requireContext())

        Log.d("ListFragment", "recyclerView: ${adapter.itemCount}")

        binding.addItemButton.setOnClickListener { _ ->
            findNavController().navigate(R.id.action_listFragment_to_addItemFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}