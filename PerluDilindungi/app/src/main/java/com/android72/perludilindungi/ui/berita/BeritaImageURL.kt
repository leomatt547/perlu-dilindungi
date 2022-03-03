package com.android72.perludilindungi.ui.berita

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView

@SuppressLint("StaticFieldLeak")
@Suppress("DEPRECATION")
class BeritaImageURL(var imageView: ImageView) : AsyncTask<String,Void, Bitmap?>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        val imgURL = params[0]
        var img: Bitmap? = null
        try {
            val `in` = java.net.URL(imgURL).openStream()
            img = BitmapFactory.decodeStream(`in`)
        }
        catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return img
    }
    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}