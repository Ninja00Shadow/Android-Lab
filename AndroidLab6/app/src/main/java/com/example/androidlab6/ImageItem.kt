package com.example.androidlab6

import android.net.Uri

class ImageItem {
    var name: String = "Empty name"
    var uripath: String = "Empty uri"
    var path: String = "Empty path"
    var curi: Uri? = null

    constructor()

    constructor(name: String, uripath: String, path: String, curi: Uri) : this() {
        this.name = name
        this.uripath = uripath
        this.path = path
        this.curi = curi
    }

    override fun equals(other: Any?): Boolean {
        return if (other is ImageItem) {
            name == other.name && uripath == other.uripath && path == other.path
        } else false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + uripath.hashCode()
        result = 31 * result + path.hashCode()
        result = 31 * result + (curi?.hashCode() ?: 0)
        return result
    }

}