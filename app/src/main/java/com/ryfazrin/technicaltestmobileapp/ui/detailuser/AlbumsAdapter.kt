package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    private val listPhotos: ArrayList<List<PhotosResponseItem>>
) : RecyclerView.Adapter<AlbumsAdapter.ListViewHolder>()  {

    inner class ListViewHolder(private val binding: ItemRowAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

//        fun bind(album: AlbumsResponseItem) {
        fun bind(
        album: AlbumsResponseItem
//        photos: List<PhotosResponseItem>
    ) {
        val photoDummy = listOf<PhotosResponseItem>(
            PhotosResponseItem(
                1,
                100,
                "photo satu",
                "ini url satu",
                "https://via.placeholder.com/150/8e973b"
            ),
            PhotosResponseItem(
                2,
                200,
                "photo dua",
                "ini url dua",
                "https://via.placeholder.com/150/121fa4"
            )
        )
            with(binding) {
                tvTitleAlbum.text = album.title
//                tvTitlePhotos.text = "${photos.id}. ${photos.title}"
                val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
//                val layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
                rvAlbumPhotos.layoutManager = layoutManager
                val adapter = PhotosAdapter(photoDummy)
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
//            Log.e("AlbumsAdapter", "onBindViewHolder $position: ${listPhotos[1]}")
//            holder.bind(listAlbum[position], listPhotos[position])
            holder.bind(listAlbum[position])
        }
    }

    override fun getItemCount(): Int = listAlbum.size
}