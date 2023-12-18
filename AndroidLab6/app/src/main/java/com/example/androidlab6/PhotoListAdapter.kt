package com.example.androidlab6

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab6.databinding.GridItemBinding
import java.io.FileNotFoundException
import java.io.InputStream
import androidx.recyclerview.widget.ListAdapter

class PhotoListAdapter(val appContext: Context) :
    ListAdapter<ImageItem, PhotoListAdapter.MyViewHolder>(DiffCallback)
{
        inner class MyViewHolder(binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val img = binding.imageView
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
           currentItem.curi?.let {
                holder.img.setImageBitmap(appContext.contentResolver.loadThumbnail(it,
                    Size(72,72), null))
            }
        }
        else
            holder.img.setImageBitmap(getBitmapFromUri(appContext, currentItem.curi))


        holder.itemView.setOnClickListener {
            findNavController(holder.itemView).navigate(R.id.action_global_imageSwipeFragment, bundleOf(
                "startPosition" to position)
            )
        }
    }

    private fun getBitmapFromUri(mContext: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val imageStream: InputStream

            try {
                imageStream = uri?.let {
                    mContext.contentResolver.openInputStream(it)
                }!!
                bitmap = BitmapFactory.decodeStream(imageStream)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.path == newItem.path
            }
            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.path == newItem.path
            }
        }
    }
}