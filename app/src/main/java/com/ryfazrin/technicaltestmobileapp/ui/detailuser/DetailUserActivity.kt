package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvAlbums.addItemDecoration(itemDecoration)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setAlbumsData(
        albums: List<AlbumsResponseItem>,
        photos: ArrayList<List<PhotosResponseItem>>,
    ) {

        val listAlbum = ArrayList<AlbumsResponseItem>()
        listAlbum.clear()

        var counter = 0
        while (counter < albums.size){
            listAlbum.add(albums[counter])

            Log.e("DetailUserActivity", "album id: ${albums[counter].id}")
            counter++
        }

        val adapter = AlbumsAdapter(listAlbum, photos)

        binding.rvAlbums.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var EXTRA_DETAIL_USER = "extra_detail_user"
    }
}