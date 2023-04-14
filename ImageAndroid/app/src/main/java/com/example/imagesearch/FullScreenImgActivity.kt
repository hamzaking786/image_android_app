package com.example.imagesearch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.chrisbanes.photoview.PhotoView

class FullScreenImgActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_img)


        val bundle = intent.extras
        if (bundle != null) {
            val photoView = findViewById<View>(R.id.photo_view) as PhotoView
            Glide.with(applicationContext)
                .load(bundle.getString("link"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoView)
        }
    }
}