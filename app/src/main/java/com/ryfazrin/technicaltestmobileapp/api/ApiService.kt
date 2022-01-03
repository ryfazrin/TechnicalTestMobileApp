package com.ryfazrin.technicaltestmobileapp.api

import com.ryfazrin.technicaltestmobileapp.data.DetailUserResponse
import com.ryfazrin.technicaltestmobileapp.data.PostsResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<PostsResponseItem>>

    @GET("users/{userId}")
    fun getDetailUser(
        @Path("userId") userId: Int
    ) : Call<DetailUserResponse>
}