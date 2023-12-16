package com.example.androidlab6

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.androidlab6.databinding.FragmentTakePhotoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class TakePhotoFragment : Fragment() {
    private lateinit var binding: FragmentTakePhotoBinding
    private lateinit var lastFile: File
    private lateinit var lastFileUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTakePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoPreviewLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { result: Bitmap? ->
                if (result != null) {
                    Toast.makeText(requireContext(), "PREVIEW RECEIVED", Toast.LENGTH_LONG).show()
                    binding.imageView.setImageBitmap(result)
                } else {
                    Toast.makeText(requireContext(), "PREVIEW NOT RECEIVED", Toast.LENGTH_LONG)
                        .show()
                }
            }

        binding.cameraPreviewButton.setOnClickListener {
            try {
                photoPreviewLauncher.launch(null)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "PREVIEW LAUNCHER FAILED", Toast.LENGTH_LONG)
                    .show()
            }
            binding.imageView.visibility = View.VISIBLE
        }

        val photoLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { result: Boolean ->
                if (result) {
                    // consume result - see later remarks
                    Toast.makeText(requireContext(), "Photo TAKEN", Toast.LENGTH_LONG).show()
                    binding.imageView.setImageURI(lastFileUri)
                } else {
                    // make some action – warning
                    Toast.makeText(requireContext(), "Photo NOT taken!", Toast.LENGTH_LONG).show()
                    lastFile.delete()
                }
            }

        binding.cameraTakePhotoButton.setOnClickListener {
            lastFileUri = getNewFileUri()
            try {
                photoLauncher.launch(lastFileUri)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(),"CAMERA DOESN'T WORK!", Toast.LENGTH_LONG).show()
            }
            binding.imageView.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getNewFileUri(): Uri {
        val tStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val dataRepo = DataRepo.getinstance(requireContext())

        val dir = when (dataRepo.getStorageType()) {
            DataRepo.SHARED_STORAGE -> {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            }

            DataRepo.PRIVATE_STORAGE -> {
                requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
            }

            else -> return MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }


        val tmpFile = File.createTempFile("Photo_$tStamp", ".jpg", dir)
        lastFile = tmpFile //save File for future use
        return FileProvider.getUriForFile(requireContext(),
            "com.example.androidlab6.provider",
            tmpFile)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TakePhotoFragment()
    }
}