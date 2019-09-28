package com.yoite.planethelp.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.R


class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

    }


    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}