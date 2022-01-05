package com.ryfazrin.technicaltestmobileapp.api

import com.ryfazrin.technicaltestmobileapp.data.*
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

    @GET("posts/{postId}/comments")
    fun getCommentsPost(
        @Path("postId") postId: Int
    ) : Call<List<CommentsResponseItem>>

    @GET("users/{userId}/albums")
    fun getUserAlbums(
        @Path("userId") userId: Int
    ) : Call<List<AlbumsResponseItem>>
}