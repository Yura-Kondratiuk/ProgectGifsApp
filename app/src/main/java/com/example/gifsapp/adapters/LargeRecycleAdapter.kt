package com.example.gifsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gifsapp.R
import com.example.gifsapp.model.GifModel
import com.example.gifsapp.view.GlideApp

class LargeRecycleAdapter(
): ListAdapter<GifModel, LargeRecycleAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView

        init {
            ivImage = view.findViewById(R.id.ivImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_large_gif, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            GlideApp.with(itemView.context).load(getItem(position).url).centerCrop().into(ivImage)

        }
    }

    override fun getItemCount() = currentList.size

    object DiffCallback : DiffUtil.ItemCallback<GifModel>() {

        override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel) =
            oldItem.url == newItem.url
    }
}