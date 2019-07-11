package com.leonardoalves.data_base.models

import com.google.gson.annotations.SerializedName

data class CreatedByResponse(

	@field:SerializedName("gravatar_hash")
	val gravatarHash: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)