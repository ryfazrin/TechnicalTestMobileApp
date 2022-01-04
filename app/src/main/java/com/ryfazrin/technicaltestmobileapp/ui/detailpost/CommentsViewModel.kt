package com.ryfazrin.technicaltestmobileapp.ui.detailpost

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.technicaltestmobileapp.api.ApiConfig
import com.ryfazrin.technicaltestmobileapp.data.CommentsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsViewModel : ViewModel() {

    private val _comments = MutableLiveData<List<CommentsResponseItem>>()
    val comments: LiveData<List<CommentsResponseItem>> = _comments

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getComments(postId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getCommentsPost(postId)
        client.enqueue(object : Callback<List<CommentsResponseItem>>{
            override fun onResponse(
                call: Call<List<CommentsResponseItem>>,
                response: Response<List<CommentsResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _comments.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentsResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "CommentsViewModel"
    }
}