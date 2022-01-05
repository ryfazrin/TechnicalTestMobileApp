package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowAlbumBinding

class AlbumsAdapter(private val listAlbum: ArrayList<AlbumsResponseItem>) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {

    inner class ListViewHolder(private val binding: ItemRowAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumsResponseItem) {
            with(binding) {
                tvTitleAlbum.text = album.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listAlbum[position])
    }

    override fun getItemCount(): Int = listAlbum.size
}