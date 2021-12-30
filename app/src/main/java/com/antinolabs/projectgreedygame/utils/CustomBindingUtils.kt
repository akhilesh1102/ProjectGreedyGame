package com.antinolabs.projectgreedygame.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CustomBindingUtils {
    @JvmStatic
    @BindingAdapter(value = ["setImageUrl"])
    fun ImageView.bindImageUrl(url: String?) {
        if (url != null && url.isNotBlank()) {
            Glide.with(context)
                .asBitmap()
                .load(Constant.Image_BaseUrl+url)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setImageUrlCir"])
    fun de.hdodenhof.circleimageview.CircleImageView.bindImageUrl(url: String?) {
        if (url != null && url.isNotBlank()) {
            Glide.with(context)
                .asBitmap()
                .load(Constant.Image_BaseUrl+url)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }else{
            Glide.with(context)
                .asBitmap()
                .load("https://miro.medium.com/max/1400/1*83Ak_kQymYMfgQEg2EtvCA.jpeg")
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(false)
            this.adapter = adapter
            this.isNestedScrollingEnabled = false
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setLanguage"])
    fun TextView.bindLang(lang:String?) {
        this.run {
            lang.let {
                val loc = Locale(it)
                this.text="Language : ${loc.getDisplayLanguage(loc)}"
            }

        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setDate"])
    fun TextView.bindDate(date:String?) {
        this.run {
            date.let {
                val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                val output = SimpleDateFormat("dd/MM/yyyy")

                var d: Date? = null
                try {
                    d = input.parse(it)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
                val formatted: String = output.format(d)
                this.text=formatted
            }

        }
    }
}