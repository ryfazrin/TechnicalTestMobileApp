package com.ryfazrin.technicaltestmobileapp.data

import com.google.gson.annotations.SerializedName

data class CommentsResponse(

	@field:SerializedName("CommentsResponse")
	val commentsResponse: List<CommentsResponseItem>
)

data class CommentsResponseItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("postId")
	val postId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("email")
	val email: String
)
