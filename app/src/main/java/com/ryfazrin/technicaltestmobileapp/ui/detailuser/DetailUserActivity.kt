package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import com.ryfazrin.technicaltestmobileapp.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class DetailUserActivity : AppCompatActivity() {

    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var binding: ActivityDetailUserBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getUser: DetailUserResponse = intent.getParcelableExtra<DetailUserResponse>(
            EXTRA_DETAIL_USER
        ) as DetailUserResponse

        albumsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(AlbumsViewModel::class.java)
//        photoViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//            .get(PhotosViewModel::class.java)

        albumsViewModel.getAlbums(getUser.id)

        binding.tvUserName.text = getUser.name
        binding.tvUserEmail.text = getUser.email
        val address = getUser.address
        binding.tvUserAddress.text = "${address.street} ${address.suite}, ${address.city}, ${address.zipcode}"
        val company = getUser.company
        binding.tvUserCompanyName.text = company.name

        albumsViewModel.albums.observe(this, { albums ->
            setAlbumsData(albums, albumsViewModel.photos)
        })

        albumsViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        val layoutManager = LinearLayoutManager(this)
        binding.rvAlbums.layoutManager = layoutManager
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

//    suspend fun getDataPhotos(albumId: Int):  ArrayList<List<PhotosResponseItem>>{
//        val photoViewModel: PhotosViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
//            .get(PhotosViewModel::class.java)
//        photoViewModel.getPhotos(albumId)
//        return photoViewModel.photos
//    }

    private fun setAlbumsData(
        albums: List<AlbumsResponseItem>,
        photos: ArrayList<List<PhotosResponseItem>>
    ) {

        val listAlbum = ArrayList<AlbumsResponseItem>()
//        val listPhotos = ArrayList<List<PhotosResponseItem>>()
        listAlbum.clear()
//        listPhotos.clear()

        var counter = 0
        while (counter < albums.size){
            listAlbum.add(albums[counter])

            Log.e("DetailUserActivity", "album id: ${albums[counter].id}")
//            Log.e("DetailUserActivity", "photos: ${photos}")

//            photoViewModel.getPhotos(albums[counter].id)

//            val jobGetPhotos = GlobalScope.launch {
////                photoViewModel.getPhotos(albums[counter].id)
//                getDataPhotos(albumId)
//            }

//            runBlocking {
//                jobGetPhotos.join()

//                jobGetPhotos.join()

//                val getPhotos = async {
////                    photoViewModel.getPhotos(albums[counter].id)
//                    getDataPhotos(albums[counter].id)
//                }
//
//                getPhotos.await()

//                Log.e("DetailUserActivity", "album id: ${albums[counter].id}")
//                Log.e("DetailUserActivity", "photos: ${}")
//            }

            counter++
        }

        val adapter = AlbumsAdapter(listAlbum, photos, this)

        binding.rvAlbums.adapter = adapter

//        val jobListPhotos = GlobalScope.launch {
//            var counter = 0
//            while (counter < albums.size){
//                listAlbum.add(albums[counter])
//                photoViewModel.getPhotos(albums[counter].id)
//                photoViewModel.photos.observeForever { photos ->
//                    listPhotos.add(photos)
//                }
//
//                counter++
//            }
//        }
//        //        listAlbum.addAll(albums)
//        runBlocking {
//            jobListPhotos.join()
//            val adapter = AlbumsAdapter(listAlbum, listPhotos)
//
//            binding.rvAlbums.adapter = adapter
//        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var EXTRA_DETAIL_USER = "extra_detail_user"
    }
}