package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ItemRowAlbumBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//class AlbumsAdapter(private val listAlbum: ArrayList<AlbumsResponseItem>) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {
@DelicateCoroutinesApi
class AlbumsAdapter(
    private val listAlbum: ArrayList<AlbumsResponseItem>,
    private val listPhotos: List<PhotosResponseItem>,
    private val detailUserActivity: DetailUserActivity
) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {

    inner class ListViewHolder(private val binding: ItemRowAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

//        fun bind(album: AlbumsResponseItem) {
        fun bind(
    album: AlbumsResponseItem,
    photos: PhotosResponseItem,
    detailUserActivity: DetailUserActivity
) {

            with(binding) {
                tvTitleAlbum.text = album.title
//                tvTitlePhotos.text = photos.title
                val layoutManager = LinearLayoutManager(detailUserActivity)
                rvAlbumPhotos.layoutManager = layoutManager
                val adapter = PhotosAdapter(listOf(photos))
                rvAlbumPhotos.adapter = adapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.bind(listAlbum[position])
        val jobListPhotos = GlobalScope.launch {
            listPhotos
        }

        runBlocking {
            jobListPhotos.join()
            Log.e("AlbumsAdapter", "onBindViewHolder $position: ${listPhotos[1]}")
            holder.bind(listAlbum[position], listPhotos[position], detailUserActivity)
        }
    }

    override fun getItemCount(): Int = listAlbum.size
}