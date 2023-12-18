package com.example.androidlab6

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

class ImageRepo {
    lateinit var uri: Uri

    @SuppressLint("Recycle")
    fun getSharedList(): MutableList<ImageItem>? {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val contentResolver: ContentResolver = ctx.contentResolver

        sharedStoreList?.clear()

        val cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            Log.e("ImageRepo", "Cursor is null")
        } else if (!cursor.moveToFirst()) {
            Log.e("ImageRepo", "Cursor is empty")
        } else {
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            do {
                var thisId = cursor.getLong(idColumn)
                var thisName = cursor.getString(nameColumn)

                var thisContentUri = ContentUris.withAppendedId(uri, thisId)
                var thisUriPath = thisContentUri.toString()
                sharedStoreList?.add(
                    ImageItem(
                        thisName,
                        thisUriPath,
                        "No path yet",
                        thisContentUri
                    )
                )
            } while (cursor.moveToNext());
        }
        cursor?.close()
        return sharedStoreList
    }

    fun setStorageType(storage: Int): Boolean {
        if (storage != SHARED_STORAGE && storage != PRIVATE_STORAGE)
            return false
        else {
            photo_storage_type = storage
        }
        return true
    }

    fun getStorageType(): Int {
        return photo_storage_type
    }

    fun getAppList(): MutableList<ImageItem>? {
        val dir: File? = ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        dir?.listFiles()
        appStoreList?.clear()
        if (dir?.isDirectory() == true) {
            var fileList = dir.listFiles()
            if (fileList != null) {
                for (value in fileList) {
                    var fileName = value.name
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                        fileName.endsWith(".png") || fileName.endsWith(".gif")
                    ) {
                        val tmpUri = FileProvider.getUriForFile(
                            ctx,
                            "com.example.androidlab6.provider", value
                        )
                        appStoreList?.add(
                            ImageItem(
                                fileName, value.toURI().path,
                                value.absolutePath, tmpUri
                            )
                        )
                    }
                }
            }
        }
        return appStoreList
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: ImageRepo? = null

        @SuppressLint("StaticFieldLeak")
        private lateinit var ctx: Context
        private var sharedStoreList: MutableList<ImageItem>? = null
        private var appStoreList: MutableList<ImageItem>? = null

        const val SHARED_STORAGE = 1
        const val PRIVATE_STORAGE = 2
        var photo_storage_type = SHARED_STORAGE
        fun getinstance(ctx: Context): ImageRepo {
            if (INSTANCE == null) {
                INSTANCE = ImageRepo()
                sharedStoreList = mutableListOf()
                appStoreList = mutableListOf()
                this.ctx = ctx
            }
            return INSTANCE as ImageRepo
        }
    }
}