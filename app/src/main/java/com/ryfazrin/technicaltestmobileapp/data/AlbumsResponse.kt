package com.ryfazrin.technicaltestmobileapp.data

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(

	@field:SerializedName("AlbumsResponse")
	val albumsResponse: List<AlbumsResponseItem>
)

data class AlbumsResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userId")
	val userId: Int
)
