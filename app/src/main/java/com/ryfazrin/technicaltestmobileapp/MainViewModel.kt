package com.ryfazrin.technicaltestmobileapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ryfazrin.technicaltestmobileapp.api.ApiConfig
import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<PostsResponseItem>>()
    val posts: LiveData<List<PostsResponseItem>> = _posts

//    private val _user = MutableLiveData<List<DetailUserResponse>>()
//    val user: LiveData<List<DetailUserResponse>> = _user

    var listUser = ArrayList<DetailUserResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isMessage = MutableLiveData<Boolean>()
    val isMessage: LiveData<Boolean> = _isMessage

    init {
        showPosts()
    }

    fun showPosts() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPosts()
        client.enqueue(object : Callback<List<PostsResponseItem>> {
            override fun onResponse(
                call: Call<List<PostsResponseItem>>,
                response: Response<List<PostsResponseItem>>
            ) {
//                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
//                        _isMessage.value = false

                        try {
                            for (post in responseBody) {
                                findUser(post.userId)
                            }
                            _isLoading.value = false
                            _posts.value = responseBody

                        } catch (e: Exception) {
                            Log.e(TAG, "gagal: $e")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<PostsResponseItem>>, t: Throwable) {
                _isLoading.value = false
                _isMessage.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findUser(getUserId: Int) {
        val client = ApiConfig.getApiService().getDetailUser(getUserId)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
//                        _user.value = responseBody
                        listUser.add(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}