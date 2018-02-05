package com.splash.wallpaper.archbasics

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso



class MainAdapter(var photos: PhotoList,var context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false)
       // Log.i("RECEIVED",photos.hits.size.toString())
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
         Log.i("RECEIVED","from main adapter ${photos.hits.size}")
        return photos.hits.size
    }


    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        Log.i("RECEIVED",photos.hits.size.toString())

        var photo: Photo
        photo = photos.hits.get(position)
        Log.i("RECEIVED",photo.previewUrl)
        if (photo.previewUrl.isNotEmpty()) {
            Log.i("RECEIVED",photo.previewUrl)
            Picasso.with(holder?.itemView!!.context)
                    .load(photo.previewUrl)
                    .into(holder.photo)
        }

    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var photo :ImageView
        init {
            photo = view.findViewById(R.id.recyclerview_photo)
        }

    }
}

