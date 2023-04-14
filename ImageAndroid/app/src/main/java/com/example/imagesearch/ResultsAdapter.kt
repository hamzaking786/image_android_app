package com.example.imagesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imagesearch.ResultsAdapter.ResultsVH
import com.makeramen.roundedimageview.RoundedImageView

class ResultsAdapter(var list: List<String>, var context: Context) : RecyclerView.Adapter<ResultsVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resultlistlayout, null)
        return ResultsVH(view)
    }

    override fun onBindViewHolder(holder: ResultsVH, position: Int) {
        Glide.with(context)
            .load(list[position])
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.iv)
        holder.itemView.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position])
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ResultsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv: RoundedImageView

        init {
            iv = itemView.findViewById(R.id.iv)
        }
    }
}