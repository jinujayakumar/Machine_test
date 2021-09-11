package com.example.kolomachinetest.uils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kolomachinetest.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {

    fun loadPallet(view: View, context: Context) = object : RequestListener<Drawable> {

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            if (resource is BitmapDrawable) {
                val builder = Palette.Builder(resource.bitmap)
                val palette = builder.generate { palette: Palette? ->
                    //access palette instance here
                    val data = palette?.vibrantSwatch
                    if (data != null) {
                        view.setBackgroundColor(data.rgb)
                    }
                }
            } else {
                view.setBackgroundColor(
                    context.resources
                        .getColor(R.color.black)
                )
            }
            return false
        }
    }

    fun getMD5Hash(s: String): String {
        val md5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest
                .getInstance(md5)
            digest.update(s.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}