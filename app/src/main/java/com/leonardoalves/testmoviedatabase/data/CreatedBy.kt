package com.leonardoalves.testmoviedatabase.data

import com.google.gson.annotations.SerializedName

data class CreatedBy(

	@field:SerializedName("gravatar_hash")
	val gravatarHash: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)