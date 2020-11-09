package com.test.theguardian_test.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.theguardian_test.domain.Article

@Entity
data class DatabaseArticle(
    @PrimaryKey
    var id: String,
    var webTitle: String,
    var trailText: String,
    var thumbnail: String
)

fun List<DatabaseArticle>.asDomainModel() : List<Article> {
    return map{
    Article(
        id = it.id,
        webTitle = it.webTitle,
        trailText = it.trailText,
        thumbnail = it.thumbnail
    )
    }
}