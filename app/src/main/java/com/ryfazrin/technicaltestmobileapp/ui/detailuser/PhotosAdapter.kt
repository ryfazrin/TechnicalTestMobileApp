package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowPhotoBinding

class PhotosAdapter(private val listPhoto: List<PhotosResponseItem>) : RecyclerView.Adapter<PhotosAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotosResponseItem) {
            with(binding) {
                tvPhotoTitle.text = photo.title
                Glide.with(itemView.context)
                    .load(photo.thumbnailUrl)
                    .into(imgPhotoThumbnail)
            }
            Log.e("PhotosAdapter", "bind: ${photo.thumbnailUrl}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPhoto[position])
    }

    override fun getItemCount(): Int = listPhoto.size
}