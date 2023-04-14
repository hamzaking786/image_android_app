package com.example.imagesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imagesearch.Models.ResultModel
import com.example.imagesearch.SavedAdapter.SavedVH
import com.makeramen.roundedimageview.RoundedImageView

class SavedAdapter(var list: List<ResultModel>, var context: Context) : RecyclerView.Adapter<SavedVH>() {


    var databaseHelper: DatabaseConnect

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.savedlistlayout, null)
        return SavedVH(view)
    }

    override fun onBindViewHolder(holder: SavedVH, position: Int) {
        Glide.with(context)
            .load(list[position].original)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.oiv)
        Glide.with(context)
            .load(list[position].result1)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.r1iv)
        Glide.with(context)
            .load(list[position].result2)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.r2iv)
        Glide.with(context)
            .load(list[position].result3)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.r3iv)
        Glide.with(context)
            .load(list[position].result4)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.r4iv)
        Glide.with(context)
            .load(list[position].result5)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.r5iv)
        holder.oiv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].original)
            context.startActivity(i)
        }
        holder.delIv.setOnClickListener {
            databaseHelper.deleteResult(list[position].id)
            list = databaseHelper.fetchResults()
            notifyDataSetChanged()
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
        }
        holder.r1iv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].result1)
            context.startActivity(i)
        }
        holder.r2iv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].result2)
            context.startActivity(i)
        }
        holder.r3iv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].result3)
            context.startActivity(i)
        }
        holder.r4iv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].result4)
            context.startActivity(i)
        }
        holder.r5iv.setOnClickListener {
            val i = Intent(context, FullScreenImgActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("link", list[position].result5)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SavedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var oiv: RoundedImageView
        var r1iv: RoundedImageView
        var r2iv: RoundedImageView
        var r3iv: RoundedImageView
        var r4iv: RoundedImageView
        var r5iv: RoundedImageView
        var delIv: ImageView

        init {
            oiv = itemView.findViewById(R.id.oiv)
            r1iv = itemView.findViewById(R.id.r1iv)
            r2iv = itemView.findViewById(R.id.r2iv)
            r3iv = itemView.findViewById(R.id.r3iv)
            r4iv = itemView.findViewById(R.id.r4iv)
            r5iv = itemView.findViewById(R.id.r5iv)
            delIv = itemView.findViewById(R.id.delIv)
        }
    }

    init {
        databaseHelper = DatabaseConnect(context)
    }
}