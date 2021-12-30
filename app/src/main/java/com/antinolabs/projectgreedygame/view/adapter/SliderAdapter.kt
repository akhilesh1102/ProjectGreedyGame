package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.model.NowPlaying
import com.antinolabs.projectgreedygame.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SliderAdapter
    (private var result:List<NowPlaying.Result>) : PagerAdapter() {
    override fun getCount(): Int {
        return result.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.8f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout: View =
            LayoutInflater.from(container.context)
                .inflate(R.layout.slider_item_container, container, false)!!

        val image = imageLayout
            .findViewById<View>(R.id.img_logo) as ImageView


        container.addView(imageLayout)

        Glide.with(imageLayout)
            .asBitmap()
            .load(Constant.Image_BaseUrl+result[position].poster_path)
            .centerInside()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)


        return imageLayout
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


}
