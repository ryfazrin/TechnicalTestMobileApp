package com.ryfazrin.technicaltestmobileapp.data

import com.google.gson.annotations.SerializedName

data class PhotosResponse(

	@field:SerializedName("PhotosResponse")
	val photosResponse: List<PhotosResponseItem>
)

data class PhotosResponseItem(

	@field:SerializedName("albumId")
	val albumId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String
)
