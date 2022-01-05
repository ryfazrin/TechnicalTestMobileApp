package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.technicaltestmobileapp.api.ApiConfig
import com.ryfazrin.technicaltestmobileapp.data.AlbumsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel : ViewModel() {

    private val _albums = MutableLiveData<List<AlbumsResponseItem>>()
    val albums: LiveData<List<AlbumsResponseItem>> = _albums

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
                        _albums.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<AlbumsResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "AlbumsViewModel"
    }
}