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

class RecycleAdapter(
    val onClick: (Int) -> Unit,
    val onLongClick: (String, Int) -> Unit
) : ListAdapter<GifModel, RecycleAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView

        init {
            ivImage = view.findViewById(R.id.imGif)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_gif, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            GlideApp.with(itemView.context).asGif().load(getItem(position).url)
                .placeholder(R.drawable.gif_preview).centerCrop().into(ivImage)
            itemView.setOnClickListener { onClick(adapterPosition) }
            itemView.setOnLongClickListener {
                onLongClick(getItem(position).id, adapterPosition)
                true
            }
        }
    }

    override fun getItemCount() = currentList.size

    object DiffCallback : DiffUtil.ItemCallback<GifModel>() {

        override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel) =
            oldItem.url == newItem.url
    }
}