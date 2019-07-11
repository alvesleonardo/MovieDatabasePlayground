package com.leonardoalves.data_base.models

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

	@field:SerializedName("comments")
	val comments: CommentsResponse? = null,

	@field:SerializedName("iso_3166_1")
	val iso31661: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("average_rating")
	val averageRating: Double? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("created_by")
	val createdBy: CreatedByResponse? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("revenue")
	val revenue: String? = null,

	@field:SerializedName("public")
	val jsonMemberPublic: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieResponse>? = null
)