package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowAlbumBinding
import com.ryfazrin.technicaltestmobileapp.ui.detailpost.CommentsViewModel

//class AlbumsAdapter(private val listAlbum: ArrayList<AlbumsResponseItem>) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {
class AlbumsAdapter(private val listAlbum: ArrayList<AlbumsResponseItem>, private val listPhotos: ArrayList<List<PhotosResponseItem>>) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {

    inner class ListViewHolder(private val binding: ItemRowAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

//        fun bind(album: AlbumsResponseItem) {
        fun bind(album: AlbumsResponseItem, photos: List<PhotosResponseItem>) {

            with(binding) {
                tvTitleAlbum.text = album.title
//                val layoutManager = LinearLayoutManager(this@AlbumsAdapter)
//                rvAlbumPhotos.layoutManager = layoutManager
                val adapter = PhotosAdapter(photos)
                rvAlbumPhotos.adapter = adapter
//                Log.e("AlbumsAdapter", "bind: $photos")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.bind(listAlbum[position])
        holder.bind(listAlbum[position], listPhotos[position])
    }

    override fun getItemCount(): Int = listAlbum.size
}