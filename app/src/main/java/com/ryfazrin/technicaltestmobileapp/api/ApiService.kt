package com.ryfazrin.technicaltestmobileapp.api

import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<PostsResponseItem>>
}