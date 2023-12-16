package com.example.androidlab6

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.example.androidlab6.databinding.FragmentStartBinding
import java.io.File

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private var basePhotoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    private val externalStorageDirectory = Environment.getExternalStorageDirectory()
    private val externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    private var externalFilesDirPictures : File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        externalFilesDirPictures = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        binding.basePhotoUriTextView.text = basePhotoUri.scheme + ":/" + MediaStore.Images.Media.EXTERNAL_CONTENT_URI.path
        binding.externalStorageTextView.text = externalStorageDirectory.absolutePath
        binding.externalStoragePublicDirectoryTextView.text = externalStoragePublicDirectory.absolutePath
        binding.externalStoragePublicDirectoryPicturesTextView.text = externalFilesDirPictures?.absolutePath ?: "nothing"
        externalFilesDirPictures?.let {
            val theuri = FileProvider.getUriForFile(requireContext(),
                "com.example.androidlab6.provider", it)
            binding.other.text = theuri.scheme + ":/" + theuri.path
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }
}