package com.ryfazrin.technicaltestmobileapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUserResponse(

	@field:SerializedName("website")
	val website: String,

	@field:SerializedName("address")
	val address: Address,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: Company,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
) : Parcelable

@Parcelize
data class Address(

	@field:SerializedName("zipcode")
	val zipcode: String,

	@field:SerializedName("geo")
	val geo: Geo,

	@field:SerializedName("suite")
	val suite: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("street")
	val street: String
) : Parcelable

@Parcelize
data class Company(

	@field:SerializedName("bs")
	val bs: String,

	@field:SerializedName("catchPhrase")
	val catchPhrase: String,

	@field:SerializedName("name")
	val name: String
) : Parcelable

@Parcelize
data class Geo(

	@field:SerializedName("lng")
	val lng: String,

	@field:SerializedName("lat")
	val lat: String
) : Parcelable
