

package com.ali.moviesApp.main.common

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.ali.moviesApp.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("bindServerDate")
fun bindServerDate(@NonNull textView: TextView, dateString: String?) {
    var dateStr = ""
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            //  Sat 10 November 2018
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "EEE d MMM yyyy",
                Locale("en")
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}

@BindingAdapter("imageBinding")
fun imageBinding(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        view.setImageResource(R.drawable.move_placeholder)
        return
    }

    val requestOptions: RequestOptions = RequestOptions()
        .downsample(DownsampleStrategy.CENTER_INSIDE)
        .format(DecodeFormat.PREFER_RGB_565)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .encodeFormat(Bitmap.CompressFormat.WEBP)

    Glide.with(view.context)
        .asBitmap()
        .load(imageUrl)
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.move_placeholder)
        .error(R.drawable.move_placeholder)
        .fallback(R.drawable.move_placeholder)
        .apply(requestOptions)
        .into(view)
}



@BindingAdapter("notNullText")
fun text(textView: TextView, str: String?) {
    textView.text = if (str.isNullOrEmpty() || str.trim { it <= ' ' }
            .isEmpty() || str === "null") "" else str.replace("\"", "")
}