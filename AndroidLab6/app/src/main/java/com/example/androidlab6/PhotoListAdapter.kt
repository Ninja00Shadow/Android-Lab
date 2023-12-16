//package com.example.androidlab6
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Build
//import android.util.Size
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.androidlab6.databinding.ListRowBinding
//import java.io.FileNotFoundException
//import java.io.InputStream
//
//class PhotoListAdapter(val appContext: Context, val dList: MutableList<DataItem>) :
//    RecyclerView.Adapter<PhotoListAdapter.MyViewHolder>()
//{
//        inner class MyViewHolder(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
//            val itemName: TextView = binding.itemName
//            val uri: TextView = binding.itemUri
//            val curi: TextView = binding.itemCuri
//            val img = binding.itemIcon
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val viewBinding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(viewBinding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.binding.itemName.text = dList[position].name
//        holder.binding.itemUri.text = dList[position].uripath
//        holder.binding.itemCuri.text = dList[position].curi?.path
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//            dList[position].curi?.let {
//                holder.img.setImageBitmap(appContext.contentResolver.loadThumbnail(it,
//                    Size(72,72), null))
//            }
//        }
//        else
//            holder.img.setImageBitmap(getBitmapFromUri(appContext, dList[position].curi))
//    }
//
//    private fun getBitmapFromUri(mContext: Context, uri: Uri?): Bitmap? {
//        var bitmap: Bitmap? = null
//        try {
//            val imageStream: InputStream
//
//            try {
//                imageStream = uri?.let {
//                    mContext.contentResolver.openInputStream(it)
//                }!!
//                bitmap = BitmapFactory.decodeStream(imageStream)
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return bitmap
//    }
//
//    override fun getItemCount(): Int {
//        return dList.size
//    }
//}