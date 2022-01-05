package com.ryfazrin.technicaltestmobileapp.ui.detailuser

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ryfazrin.technicaltestmobileapp.api.ApiConfig
import com.ryfazrin.technicaltestmobileapp.data.PhotosResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {

    private val _photos = ArrayList<List<PhotosResponseItem>>()
    val photos: ArrayList<List<PhotosResponseItem>> = _photos

    fun getPhotos(albumId: Int) {
//    fun getPhotos(albumId: Int): List<PhotosResponseItem> {
//        var r: List<PhotosResponseItem> = ArrayList<PhotosResponseItem>()

        val client = ApiConfig.getApiService().getAlbumPhotos(albumId)
        client.enqueue(object : Callback<List<PhotosResponseItem>> {
            override fun onResponse(
                call: Call<List<PhotosResponseItem>>,
                response: Response<List<PhotosResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
//                            val tes = async {
//                                responseBody
//                            }
                            _photos.add(responseBody)
//                        Log.e("PhotosViewModel", "responseBody: $photos")
//                        r = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<PhotosResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
//        return r
//        Log.e("PhotosViewModel", "response: $_photos")
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}