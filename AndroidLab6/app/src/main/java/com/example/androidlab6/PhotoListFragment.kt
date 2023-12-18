package com.example.androidlab6

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlab6.databinding.FragmentPhotoListBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class PhotoListFragment : Fragment() {
    private lateinit var binding: FragmentPhotoListBinding

    private lateinit var photoLauncher : ActivityResultLauncher<Uri>

    private var adapter : PhotoListAdapter? = null
    private lateinit var imageRepo : ImageRepo

    private var lastFile : File? = null
    private var lastFileUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageRepo = ImageRepo.getinstance(requireContext())
        adapter = PhotoListAdapter(requireContext())

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataRepo = ImageRepo.getinstance(requireContext())

        Log.d("StorageType", dataRepo.getStorageType().toString())
        Log.d("SharedList", dataRepo.getSharedList().toString())
        Log.d("AppList", dataRepo.getAppList().toString())

        val photoList = dataRepo.getSharedList()
        if (photoList == null) {
            Toast.makeText(requireContext(), "Invalid Data", Toast.LENGTH_LONG).show()
            requireActivity().onBackPressed()
        }

        when (dataRepo.getStorageType()) {
            ImageRepo.SHARED_STORAGE -> {
                adapter?.submitList(dataRepo.getSharedList())
            }

            ImageRepo.PRIVATE_STORAGE -> {
                adapter?.submitList(dataRepo.getAppList())
            }
        }

        photoLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { result: Boolean ->
                if (result) {
                    Toast.makeText(requireContext(), "PHOTO TAKEN", Toast.LENGTH_LONG).show()

                    when(dataRepo.getStorageType()) {
                        ImageRepo.SHARED_STORAGE -> {
                            adapter?.submitList(dataRepo.getSharedList())
                        }

                        ImageRepo.PRIVATE_STORAGE -> {
                            adapter?.submitList(dataRepo.getAppList())
                        }
                    }
                    requireActivity().recreate()

                } else {
                    Toast.makeText(requireContext(), "PHOTO NOT TAKEN", Toast.LENGTH_LONG).show()
                }
            }

        binding.takePhotoButton.setOnClickListener {
            takePhoto(adapter)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getNewFileUri(): Uri {
        val tStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val dataRepo = ImageRepo.getinstance(requireContext())

        val dir = when (dataRepo.getStorageType()) {
            ImageRepo.SHARED_STORAGE -> {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            ImageRepo.PRIVATE_STORAGE -> {
                requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
            }

            else -> return MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        Log.d("Dir", dir.toString())

        val tmpFile = File.createTempFile("Photo_$tStamp", ".jpg", dir)
        lastFile = tmpFile //save File for future use
        return FileProvider.getUriForFile(
            requireContext(),
            "com.example.androidlab6.provider",
            tmpFile
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.photo_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataRepo = ImageRepo.getinstance(requireContext())

        when (item.itemId) {
            R.id.action_take_photo -> {
                takePhoto(adapter)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun takePhoto(adapter: PhotoListAdapter?) {
        lastFileUri = getNewFileUri()
        photoLauncher.launch(lastFileUri)

//        when(imageRepo.getStorageType()) {
//            ImageRepo.SHARED_STORAGE -> {
//                adapter?.submitList(imageRepo.getSharedList())
//            }
//
//            ImageRepo.PRIVATE_STORAGE -> {
//                adapter?.submitList(imageRepo.getAppList())
//            }
//        }
//        requireActivity().recreate()
    }

    private fun openImageSwipeFragment(uripath: String) {
        findNavController().navigate(
            R.id.action_global_imageSwipeFragment,
            bundleOf("uripath" to uripath)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotoListFragment()
    }
}