package com.ryfazrin.technicaltestmobileapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsResponse(

	@field:SerializedName("PostsResponse")
	val postsResponse: List<PostsResponseItem>
) : Parcelable

@Parcelize
data class PostsResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("userId")
	val userId: Int
) : Parcelable
