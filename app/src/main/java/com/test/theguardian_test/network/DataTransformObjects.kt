package com.test.theguardian_test.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.theguardian_test.database.DatabaseArticle

@JsonClass(generateAdapter = true)
data class NetworkArticleContainer(

	@Json(name="response")
	val response: Response
)

@JsonClass(generateAdapter = true)
data class Fields(

	@Json(name="thumbnail")
	val thumbnail: String,

	@Json(name="trailText")
	val trailText: String
)

@JsonClass(generateAdapter = true)
data class Response(
	@Json(name="results")
	val results: List<ResultsItem>
)

@JsonClass(generateAdapter = true)
data class ResultsItem(
	@Json(name="webTitle")
	val webTitle: String,

	@Json(name="id")
	val id: String,

	@Json(name="fields")
	val fields: Fields
)

fun NetworkArticleContainer.asDatabaseModel() : List<DatabaseArticle> {
	return response.results.map {
		DatabaseArticle(
			id = it.id,
			webTitle = it.webTitle,
			trailText = it.fields.trailText,
			thumbnail = it.fields.thumbnail

		)
	}
}