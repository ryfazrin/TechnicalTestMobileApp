package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.technicaltestmobileapp.api.ApiConfig
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import com.ryfazrin.technicaltestmobileapp.ui.main.MainViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel : ViewModel() {

    private val _albums = MutableLiveData<List<AlbumsResponseItem>>()
    val albums: LiveData<List<AlbumsResponseItem>> = _albums

    var photos = ArrayList<List<PhotosResponseItem>>()

    private val _photos = MutableLiveData<List<PhotosResponseItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    @DelicateCoroutinesApi
    fun getAlbums(userId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserAlbums(userId)
        client.enqueue(object : Callback<List<AlbumsResponseItem>> {
            override fun onResponse(
                call: Call<List<AlbumsResponseItem>>,
                response: Response<List<AlbumsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        try {
                            val jobGetPhoto = GlobalScope.launch {
                                for (albums in responseBody) {
                                    getPhotos(albums.id)?.let { photos.add(it) }
                                    Log.e(TAG, "onResponse getAlbums: $photos")
                                }
                            }

                            runBlocking {
                                jobGetPhoto.join()
                                _isLoading.value = false
                                _albums.value = responseBody
                            }

                        } catch (e: Exception) {
                            Log.e(TAG, "gagal: $e")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<AlbumsResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getPhotos(albumId: Int): List<PhotosResponseItem>? {
        val client = ApiConfig.getApiService().getAlbumPhotos(albumId)
        client.enqueue(object : Callback<List<PhotosResponseItem>> {
            override fun onResponse(
                call: Call<List<PhotosResponseItem>>,
                response: Response<List<PhotosResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _photos.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<PhotosResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        Log.e(TAG, "onResponse getPhotos: ${_photos.value}")
        return _photos.value
    }

    companion object {
        private const val TAG = "AlbumsViewModel"
    }
}